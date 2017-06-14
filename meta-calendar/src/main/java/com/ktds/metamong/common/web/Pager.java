package com.ktds.metamong.common.web;

public abstract class Pager {
	
	public static final boolean ORACLE = true;
	public static final boolean OTHER = false;
	
	private int totalArticleCount;

	protected int printArticle;
	int printPage;

	protected int startArticleNumber;
	protected int endArticleNumber;

	int totalPage;
	int totalGroup;

	int nowGroupNumber;

	int groupStartPage;

	int nextGroupPageNumber;
	int prevGroupPageNumber;

	protected int pageNo;
	
	/**
	 * Paging 객체�? ?��?��?��?��.
	 * ?�� ?��?���??�� 보여�??�� 게시�? ?�� 10�?
	 * ?�� ?��?���??�� 보여�??�� ?��?���? ?�� 10�?
	 * �? 기본 ?��?��?��.
	 */
	public Pager() {
		this.printArticle = 10;
		this.printPage = 10;
	}
	
	public Pager(int printArticle, int printPage) {
		this.printArticle = printArticle;
		this.printPage = printPage;
	}
	
	public void setPageNumber(int pageNumber) {
		setPageNumber(pageNumber + "");
	}
	
	/**
	 * ?���??�� ?��?���??�� 번호�? ?��?��?��?��.
	 * 1 ?��?���??�� 경우 0?�� ?��?��?��?��.
	 * ?��무것?�� ?��?��?���? ?��?��?���? 0?���? 기본 ?��?��?��?��.
	 * @param pageNumber
	 */
	public void setPageNumber(String pageNumber) {
		this.pageNo = 0;
		try {
			this.pageNo = Integer.parseInt(pageNumber);
		} catch (NumberFormatException nfe) {
			this.pageNo = 0;
		}

		computeArticleNumbers();
		
		this.nowGroupNumber = this.pageNo / this.printPage;
		this.groupStartPage = (this.nowGroupNumber * this.printPage) + 1;

		this.nextGroupPageNumber = this.groupStartPage + this.printPage - 1;
		this.prevGroupPageNumber = this.groupStartPage - this.printPage - 1;
	}
	
	protected abstract void computeArticleNumbers();
	
	/**
	 * 조회?��?��?�� 조건(Query)?�� �? 게시�? ?���? ?��?��?��?��.
	 * @param count
	 */
	public void setTotalArticleCount(int count) {
		this.totalArticleCount = count;

		this.totalPage = (int) Math.ceil((double) this.totalArticleCount
				/ this.printArticle);
		this.totalGroup = (int) Math.ceil((double) this.totalPage
				/ this.printPage);
	}
	
	/**
	 * 조회?��?��?�� 조건(Query)?�� �? 게시�? ?���? �??��?��?��.
	 * @return
	 */
	public int getTotalArticleCount() {
		return this.totalArticleCount;
	}

	/**
	 * Query?��?�� ?��?��?�� ?��?�� ?��?�� 번호 
	 * Oracle?�� 경우 rownum?�� ?��?�� 번호
	 * @return
	 */
	public int getStartArticleNumber() {
		return this.startArticleNumber;
	}
	
	/**
	 * Query?��?�� ?��?��?�� ?��?�� ?��?�� 번호�? ?��?��?��?��.
	 * @param startArticleNumber
	 */
	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}
	
	/**
	 * Query?��?�� ?��?��?�� ?��?�� ?�� 번호�? ?��?��?��?��.
	 * @param endArticleNumber
	 */
	public abstract void setEndArticleNumber(int endArticleNumber);

	/**
	 * Query?��?�� ?��?��?�� ?��?�� 마�?�? 번호
	 * Oracle?�� 경우 rownum?�� 마�?�? 번호
	 * @return
	 */
	public abstract int getEndArticleNumber();
	
}
