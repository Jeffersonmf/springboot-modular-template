package br.com.santander.telemetria.core.events;

import org.springframework.scheduling.annotation.Async;

public class TelemetriaIngestorEvents {

    public TelemetriaIngestorEvents() {
    }

    @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("Execute method asynchronously. "
                + Thread.currentThread().getName());
    }

//    public @ResponseBody CompletableFuture<Telemetria> test(@RequestParam(value = "email", required = true) String email) throws InterruptedException {
//        return userService.findByEmail(email).thenApplyAsync(user -> {
//            return user;
//        })
//    }

//    public CompletedFuture<TelemetriaLite> postAsyncData() {
//
//        return null;
//    }

}
