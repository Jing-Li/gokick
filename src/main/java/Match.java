import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Match implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4396663089256644845L;
	Date time;
	int memberCount ;
	int MatchCount = 0;
	Map<String,String> members = new HashMap<String,String>();
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public int getMatchCount() {
		return MatchCount;
	}
	public void setMatchCount(int matchCount) {
		MatchCount = matchCount;
	}
	public Map<String,String> getMembers() {
		if(members == null){
			members = new HashMap<String,String>();
		}
		return members;
	}
	public void setMembers(Map<String,String> members) {
		this.members = members;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("*比赛时间是 ： ").append(DateFormat.getDateInstance().format(time)).append(" *\n");
		sb.append("*比赛人数是 ： ").append(memberCount).append(" * \n");
		sb.append("累计比赛场次： ").append(MatchCount).append(" * \n");
		sb.append("参加比赛场成员分别是 ： \n");
		if(members == null){
			members = new HashMap<String,String>();
		}
		for(String m : members.keySet()){
			sb.append(members.get(m)).append("\n");
		}
		
		return sb.toString();
	}
	
	
	
}
