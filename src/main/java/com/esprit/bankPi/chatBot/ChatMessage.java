package com.esprit.bankPi.chatBot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="data_Messages")
public class ChatMessage {
	 @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	 private String sender;
	 @Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true)
	private String content;
	 @Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true)
	private MessageType type;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}
	 @Column(name = "MessageType", unique = false, nullable = false, insertable = true, updatable = true)
	    private MessageType MessageType ;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
