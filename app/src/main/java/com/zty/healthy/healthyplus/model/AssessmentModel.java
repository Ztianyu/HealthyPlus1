package com.zty.healthy.healthyplus.model;

import java.util.List;

public class AssessmentModel {

	// {
	// “head”:{
	// “ret”:0,
	// “msg”:”成功”，
	// “assessList”：[{
	// “id”:” id”
	// “typeId”:”分类id”
	// “typeNm”:”评估分类”
	// “score”:”分数”
	// “gradeNm”:”评估结果”
	// “questNm”:”评估项目”
	// “doctorNm”:”医生姓名”
	// “createDate”:”评估时间”
	// }…],
	// “adviseList”：[{
	// “id”:” id”
	// “content”:”健康建议”
	// “doctorNm”:”医生姓名”
	// “createDate”:”建议时间”
	// }…]
	// },
	// “data”: null
	// }

	private Head head;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public class Head {
		private String msg;
		private int ret;
		private List<Assessment> assessList;
		private List<Advise> adviseList;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public int getRet() {
			return ret;
		}

		public void setRet(int ret) {
			this.ret = ret;
		}

		public List<Assessment> getAssessList() {
			return assessList;
		}

		public void setAssessList(List<Assessment> assessList) {
			this.assessList = assessList;
		}

		public List<Advise> getAdviseList() {
			return adviseList;
		}

		public void setAdviseList(List<Advise> adviseList) {
			this.adviseList = adviseList;
		}
	}

	public class Assessment {
		private String id;
		private String typeId;
		private String typeNm;
		private String score;
		private String gradeNm;
		private String questNm;
		private String doctorNm;
		private String createDate;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTypeId() {
			return typeId;
		}

		public void setTypeId(String typeId) {
			this.typeId = typeId;
		}

		public String getTypeNm() {
			return typeNm;
		}

		public void setTypeNm(String typeNm) {
			this.typeNm = typeNm;
		}

		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}

		public String getGradeNm() {
			return gradeNm;
		}

		public void setGradeNm(String gradeNm) {
			this.gradeNm = gradeNm;
		}

		public String getQuestNm() {
			return questNm;
		}

		public void setQuestNm(String questNm) {
			this.questNm = questNm;
		}

		public String getDoctorNm() {
			return doctorNm;
		}

		public void setDoctorNm(String doctorNm) {
			this.doctorNm = doctorNm;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
	}

	public class Advise {
		private String id;
		private String content;
		private String doctorNm;
		private String createDate;
		private String doctorImg;
		private String doctorThumbImg;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getDoctorNm() {
			return doctorNm;
		}

		public void setDoctorNm(String doctorNm) {
			this.doctorNm = doctorNm;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public String getDoctorImg() {
			return doctorImg;
		}

		public void setDoctorImg(String doctorImg) {
			this.doctorImg = doctorImg;
		}

		public String getDoctorThumbImg() {
			return doctorThumbImg;
		}

		public void setDoctorThumbImg(String doctorThumbImg) {
			this.doctorThumbImg = doctorThumbImg;
		}
	}

}
