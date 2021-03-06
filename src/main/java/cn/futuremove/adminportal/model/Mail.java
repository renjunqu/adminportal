package cn.futuremove.adminportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;
import cn.futuremove.adminportal.model.param.MailParameter;

/**
 *
 *
 */
@Entity
@Table(name = "mail")
@Cache(region = "all", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class Mail extends MailParameter {

	private static final long serialVersionUID = 7965864882079260448L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "recipient", length = 1000)
	private String recipient;
	@Column(name = "sender", length = 30)
	private String sender;
	@Column(name = "subject", length = 200)
	private String subject;
	@Column(name = "message", columnDefinition = "LONGTEXT")
	private String message;

	public Mail() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Mail other = (Mail) obj;
		return Objects.equal(this.id, other.id) && Objects.equal(this.recipient, other.recipient) && Objects.equal(this.sender, other.sender) && Objects.equal(this.subject, other.subject)
				&& Objects.equal(this.message, other.message);
	}

	//@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.recipient, this.sender, this.subject, this.message);
	}

}
