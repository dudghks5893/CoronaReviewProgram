package model;

public class Markets {
	//�ʵ庯�� (���̺� ���� ����)
	private int MARKETID;
	private int CATEID;
	private String NAME;
	private String ADDRESS;
	private String TEL;
	private String TIME;
	private int LOCALID;
	private String IMG;
	private String REVIEWID;
	private String REVIEWTEXT;
	private String REVIEWHOUR;
	private int ReviewCount;
	private int TYPE1;
	private int TYPE2;
	private int TYPE3;
	private int TYPE4;
	private String avg;
	private int TotalAVG;
	public int getTotalAVG() {
		return TotalAVG;
	}
	public void setTotalAVG(int totalAVG) {
		TotalAVG = totalAVG;
	}
	private int REVIEMASKID;
	public int getREVIEMASKID() {
		return REVIEMASKID;
	}
	public void setREVIEMASKID(int rEVIEMASKID) {
		REVIEMASKID = rEVIEMASKID;
	}
	public String getREVIEWHOUR() {
		return REVIEWHOUR;
	}
	public void setREVIEWHOUR(String rEVIEWHOUR) {
		REVIEWHOUR = rEVIEWHOUR;
	}

	public String getAvg() {
		return avg;
	}
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public int getTYPE1() {
		return TYPE1;
	}
	public void setTYPE1(int tYPE1) {
		TYPE1 = tYPE1;
	}
	public int getTYPE2() {
		return TYPE2;
	}
	public void setTYPE2(int tYPE2) {
		TYPE2 = tYPE2;
	}
	public int getTYPE3() {
		return TYPE3;
	}
	public void setTYPE3(int tYPE3) {
		TYPE3 = tYPE3;
	}
	public int getTYPE4() {
		return TYPE4;
	}
	public void setTYPE4(int tYPE4) {
		TYPE4 = tYPE4;
	}


	public int getReviewCount() {
		return ReviewCount;
	}
	public void setReviewCount(int reviewCount) {
		ReviewCount = reviewCount;
	}
	public String getREVIEWTEXT() {
		return REVIEWTEXT;
	}
	public void setREVIEWTEXT(String rEVIEWTEXT) {
		REVIEWTEXT = rEVIEWTEXT;
	}
	//������, get/set �޼ҵ� �ڵ�����
	public int getMARKETID() {
		return MARKETID;
	}
	public void setMARKETID(int mARKETID) {
		MARKETID = mARKETID;
	}
	public int getCATEID() {
		return CATEID;
	}
	public void setCATEID(int cATEID) {
		CATEID = cATEID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public int getLOCALID() {
		return LOCALID;
	}
	public void setLOCALID(int lOCALID) {
		LOCALID = lOCALID;
	}
	public String getREVIEWID() {
		return REVIEWID;
	}
	public void setREVIEWID(String rEVIEWID) {
		REVIEWID = rEVIEWID;
	}
	public String getIMG() {
		return IMG;
	}
	public void setIMG(String iMG) {
		IMG = iMG;
	}

}