package com.globomart;

import java.util.UUID;

class Message {

	private String id = UUID.randomUUID().toString();

	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
