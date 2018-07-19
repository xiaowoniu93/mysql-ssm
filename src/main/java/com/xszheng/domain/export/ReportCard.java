package com.xszheng.domain.export;

/**
 * 导出Excel 实体类
 * @author xszheng
 *
 */
public class ReportCard {

	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 年级
	 */
	private String grade;
	
	/**
	 * 语文分数
	 */
	private Integer chineseScore;
	
	/**
	 * 数学分数
	 */
	private Integer mathScore;
	
	/**
	 * 英语分数
	 */
	private Integer englishScore;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getChineseScore() {
		return chineseScore;
	}

	public void setChineseScore(Integer chineseScore) {
		this.chineseScore = chineseScore;
	}

	public Integer getMathScore() {
		return mathScore;
	}

	public void setMathScore(Integer mathScore) {
		this.mathScore = mathScore;
	}

	public Integer getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(Integer englishScore) {
		this.englishScore = englishScore;
	}

	public ReportCard(String name, String grade, Integer chineseScore, Integer mathScore, Integer englishScore) {
		super();
		this.name = name;
		this.grade = grade;
		this.chineseScore = chineseScore;
		this.mathScore = mathScore;
		this.englishScore = englishScore;
	}
	
}
