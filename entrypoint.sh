#!/bin/bash

echo "========================================="
echo "Starting application"
echo "========================================="
env | sort

##We are going to precalculate default java heap configuration:

#### When a container has its memory limited, it is reflected in /sys/fs/cgroup/memory/memory.limit_in_bytes
AVAIL_MEM=$(cat /sys/fs/cgroup/memory/memory.limit_in_bytes)
let AVAIL_MEM_MB=$AVAIL_MEM/1024/1024
mem_literal="$AVAIL_MEM_MB"m


#if its empty
if [ -z "$MEMORY_WEIGHTS" ]; then
  MEMORY_WEIGHTS="heap:70,metaspace:15,native:10"
elif ! [[ "$MEMORY_WEIGHTS" =~ heap:[0-9]+,metaspace:[0-9]+,native:[0-9]+ ]]; then
  # if it does'nt have the expected format.
  echo "WARNING: MEMORY_WEIGHTS does not have format $weights_format, $MEMORY_WEIGHTS it's not valid."
  echo "WARNING: Using default value heap:70,metaspace:15,native:10"
  MEMORY_WEIGHTS="heap:70,metaspace:15,native:10"
else
  #its not empty and have the correct format. Lets check that sum of weights sum less than 100.
  heap_size=$(echo "$MEMORY_WEIGHTS" | cut -f1 -d','| cut -f2 -d':')
  metaspace_size=$(echo "$MEMORY_WEIGHTS" | cut -f2 -d','| cut -f2 -d':')
  native_size=$(echo "$MEMORY_WEIGHTS" | cut -f3 -d','| cut -f2 -d':')
  let total=$heap_size+$metaspace_size+$native_size
  if [ "$total" -gt 100 ]; then
    echo "WARNING: Weights [$MEMORY_WEIGHTS] sums more than 100".
    echo "WARNING: Using default value heap:70,metaspace:15,native:10"
    MEMORY_WEIGHTS="heap:70,metaspace:15,native:10"
  fi
fi
echo "MEMORY_WEIGHTS:$MEMORY_WEIGHTS"


#### default Java Heap configuration
## Tool extracted from https://github.com/cloudfoundry/java-buildpack-memory-calculator
# ./java-buildpack-memory-calculator -memoryWeights=heap:70,metaspace:15,native:10 -totMemory=512m
if [ ! -z "${OPENSHIFT_REGION}" ]; then
  JAVA_HEAP=$(/opt/produban/bin/java-buildpack-memory-calculator -memoryWeights=$MEMORY_WEIGHTS -totMemory=$mem_literal)
fi

JAR_PATH=$(ls $APP)

JAVA_AGENT_01="-javaagent:$PROMETHEUS_HOME/promagent.jar=port=9300"
JAVA_AGENT_02="-javaagent:$PROMETHEUS_HOME/jmx_prometheus_javaagent.jar=agent-config:$PROMETHEUS_HOME/tomcat.yaml"
#export JAVA_PROMETHEUS="$JAVA_AGENT_01 $JAVA_AGENT_02"
#export JAVA_PROMETHEUS="-javaagent:$PROMETHEUS_HOME/promagent.jar=port=9300"
#export JAVA_PROMETHEUS="-javaagent:$PROMETHEUS_HOME/jmx_prometheus_javaagent.jar=1055:$PROMETHEUS_HOME/tomcat.yml"
export JAVA_PROMETHEUS="-javaagent:$PROMETHEUS_HOME/jmx_prometheus_javaagent.jar=agent-config:$PROMETHEUS_HOME/tomcat.yaml"

export JAVA_OPTS_EXT="$JAVA_PROMETHEUS $JAVA_OPTS_EXT"

#### if you want to add extra JVM options,, you have to use the env variable JAVA_EXT_OPTS

###echo java $JAVA_HEAP $JAVA_OPTS_EXT -jar "$JAR_PATH" $JAVA_PARAMETERS
exec java $JAVA_HEAP $JAVA_OPTS_EXT -jar "$JAR_PATH" $JAVA_PARAMETERS