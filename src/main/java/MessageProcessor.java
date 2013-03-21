import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageProcessor {
	
	public static String process(Metadata question){
		String answer = "";
		Match match = MatchUtil.readMatch();
		if(question.getType().equals("text")){
			boolean isJoin = false;
			if(question.getContent().startsWith("参加-")){
				isJoin = true;
			}else if(question.getContent().startsWith("join-")){
				isJoin = true;
			}else if(question.getContent().startsWith("好-")){
				isJoin = true;
			}else if(question.getContent().startsWith("ok-")){
				isJoin = true;
			}else if(question.getContent().startsWith("行-")){
				isJoin = true;
			}else{
				return "你的回答不匹配，下面汇报报名情况：\n" + match.toString();
			}
			
			if(isJoin){
				String name = question.getContent().split("-")[1];
				if(!containsMembe(match.getMembers(), question.getFromUserName())){
					if(!dupName(match.getMembers(),name)){
						match.getMembers().put(question.getFromUserName(), name);
						match.setMemberCount(match.getMembers().size());
						answer = "祝贺你报名成功！！！！\n"+match.toString();
					}else{
						answer = "名字："+name+"已经被使用了，请重新报名！\n"+match.toString();
					}
				}else{
					answer = "你已经参加在这次比赛中了。。。\n"+match.toString();
				}
			}
		}
		MatchUtil.writeMatch(match);
		return answer;
	}
	
	private static boolean containsMembe(Map<String,String> members,String user){
		if(members == null){
			return false;
		}
		return members.containsKey(user);
	}
	
	private static boolean dupName(Map<String,String> members,String user){
		if(members == null){
			return false;
		}
		for(String m : members.keySet()){
			if(members.get(m).equals(user)){
				return true;
			}
		}
		return false;
	}
	
}
