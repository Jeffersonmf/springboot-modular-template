i# Data Usage Query Service API´s

![POD](https://img.shields.io/badge/version-v1.0.0-blue.svg)
![POD](https://img.shields.io/badge/language-Java8-orange.svg)
![POD](https://img.shields.io/badge/platform-Linux-blue.svg)
[![POD](https://img.shields.io/badge/license-GNU-lightgrey.svg)](./LICENSE)
![Carthage compatible](https://img.shields.io/badge/Maven-compatible-red.svg?style=flat)
![Carthage compatible](https://img.shields.io/badge/SpringBoot-compatible-green.svg?style=flat)
![POD](https://img.shields.io/badge/coverage-0-yellow.svg)
![POD](https://img.shields.io/travis/rust-lang/rust.svg)

# Description

### Descriptions of project here.

# Architecture Ilustration:

images here

# Minimal Requirements

Technology | Version
------- | --------
Linux | Kernel 4.9
Java | V8
IntelliJ IDE | Recommended
SpringBoot | 1.5.8
Maven | 3.5

# Installation on the Server

# Maven / SpringBoot

Step 1 — Downloading and Installing Maven

Maven can be downloaded directly from [https://maven.apache.org](https://maven.apache.org/download.cgi)
 in zip, tar.gz packages.

Detailed steps are:

* Ensure JAVA_HOME environment variable is set and points to your JDK installation

* Extract distribution archive in any directory

```
unzip apache-maven-3.5.2-bin.zip
```
or

```
tar xzvf apache-maven-3.5.2-bin.tar.gz
```

Alternatively use your preferred archive extraction tool.

* Add the bin directory of the created directory apache-maven-3.5.2 to the PATH environment variable

* Confirm with mvn -v in a new shell. The result should look similar to

```
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T08:58:13+01:00)
Maven home: /opt/apache-maven-3.5.2
Java version: 1.8.0_45, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"
```

# Windows Tips

* Check environment variable value e.g.

```
echo %JAVA_HOME%
C:\Program Files\Java\jdk1.7.0_51
```

* Adding to PATH: Add the unpacked distribution’s bin directory to your user PATH environment variable by opening up the system properties (WinKey + Pause), selecting the “Advanced” tab, and the “Environment Variables” button, then adding or selecting the PATH variable in the user variables with the value C:\Program Files\apache-maven-3.5.2\bin. The same dialog can be used to set JAVA_HOME to the location of your JDK, e.g. C:\Program Files\Java\jdk1.7.0_51

* Open a new command prompt (Winkey + R then type cmd) and run mvn -v to verify the installation.

# Unix-based Operating System (Linux, Solaris and Mac OS X) Tips

* Check environment variable value

```
echo $JAVA_HOME
/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
```

* Adding to PATH

```
export PATH=/opt/apache-maven-3.5.2/bin:$PATH
```

# Important:

**In the application do not forget nannanana nanananaa naannaanan**

```
examples here:
```

# How to deploy and run the application


In the root folder of the application run the command:

```
mvn package or
mvn dependency:tree
```

**then Run:**

```
java -jar ./target/jefferson_ferreira_test-1.0.jar

examples here
```

# Communication

Contact the developer:
[jefferson.ferreira@truphone.com](mailto:jefferson.ferreira@truphone.com)
