package com.djs.sb.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
@Component
public class EncryptionRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("{{camel.pgp.encrypt.source.location}}")
		.autoStartup("{{camel.pgp.encrypt.autostartup}}")
		.streamCaching()
		.log("Start encryption")
		.marshal()
		.pgp("{{camel.pgp.encrypt.publicKeyFileName}}", "{{camel.pgp.crypto.keyUserId}}")
		.log("Processing file ${file:name}")
		.to("{{camel.pgp.encrypt.target.location}}?fileName=${file:name}.PGP")
		.log("Encryption ended")
		.end();
		
	}

}
