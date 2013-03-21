import org.dom4j.Document;
import org.dom4j.Element;


public class Metadata {
	private String type;
	private String content;
	private String toUserName;
	private String fromUserName;
	private int createTime;
	private String msgId;
	public Metadata(Document doc){
		Element root = doc.getRootElement();
		type = root.element("MsgType").getTextTrim();
		content = root.element("Content").getTextTrim();
		toUserName = root.element("ToUserName").getTextTrim();
		fromUserName = root.element("FromUserName").getTextTrim();
		createTime = Integer.parseInt(root.element("CreateTime").getTextTrim());
		msgId = root.element("MsgId").getTextTrim();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public int getCreateTime() {
		return createTime;
	}
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
}
