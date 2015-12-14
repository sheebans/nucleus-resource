package org.gooru.nucleus.handlers.resources.bootstrap;

import org.gooru.nucleus.handlers.resources.constants.MessagebusEndpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class ResourceVerticle  extends AbstractVerticle {
  static final Logger LOG = LoggerFactory.getLogger(ResourceVerticle.class);

  @Override
  public void start() throws Exception {
    EventBus eb = vertx.eventBus();

    eb.consumer(MessagebusEndpoints.MBEP_RESOURCE, message -> {

      System.out.println("Received message: " + message.body());
      // Now send back reply
      message.reply("Resource request received");
    }).completionHandler(result -> {
      if (result.succeeded()) {
        LOG.info("Resource end point ready to listen");        
      } else {
        LOG.error("Error registering the resource handler. Halting the Resource machinery");
        Runtime.getRuntime().halt(1);
      }
    });
  }


}
