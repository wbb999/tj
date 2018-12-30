package cn.com.tj.byhy.model;

public class Nomalquestion {
	/**
	 * 2016/4/4
	 * @author wubeibei
	 * @see 常见问题信息表
	 */
	private int id;//常见问题ID
	private String question;//问题
	private String answer;//回答
	private int account;//问题有用统计数
	/****************************************************/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	
}
