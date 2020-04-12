package com.djs.sb.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DecryptionRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		from("{{camel.pgp.decrypt.source.location}}")
		.autoStartup("{{camel.pgp.decrypt.autostartup}}")
		.streamCaching()
		.log("Starting decryption")
		.unmarshal()
		.pgp("{{camel.pgp.decrypt.privateKeyFileName}}", "{{camel.pgp.crypto.keyUserId}}", "{{camel.pgp.decrypt.password}}")
		.to("{{camel.pgp.decrypt.target.location}}?fileName=${file:name.noext.single}")
		.log("decryption for the file ${file:name} over")
		.end()
		;
	}

}
