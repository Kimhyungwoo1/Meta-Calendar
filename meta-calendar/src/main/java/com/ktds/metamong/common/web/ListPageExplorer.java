package com.ktds.metamong.common.web;

public class ListPageExplorer implements PageExplorer {

	private Pager pager;
	
	public ListPageExplorer(Pager pager) {
		this.pager = pager;
	}
	
	/**
	 * JSP?��?�� Paging 결과�? 보여�??��.
	 * getPagingList?�� &lt;form> ?���? ?��?�� ?��?��?��?��?�� ?��?��.
	 * @param link Page 번호�? ?��?��?�� Parameter Name
	 * @param pageFormat Page 번호�? 보여�? ?��?�� @(at)�? ?��?���? 번호�? 치환?��?��. [@]�? ?��?��?�� 경우 [1] [2] [3] 처럼 보여진다.
	 * @param prev ?��?�� ?��?���? 그룹?���? �??�� 버튼?�� ?��름을 ?��?��?��?��.
	 * @param next ?��?�� ?��?���? 그룹?���? �??�� 버튼?�� ?��름을 ?��?��?��?��.
	 * @param formId ?��버에�? ?��?��?�� Form ?�� ?��?��?���? ?��?��?��?��.
	 * @return
	 */
	public String getPagingList(String link, String pageFormat, String prev, String next, String formId) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<script>");
		buffer.append("function movePage(pageNo) {");
		buffer.append("$(\"#"+link+"\").val(pageNo);");
		buffer.append("$(\"#"+formId+"\").attr('action', '');");
		buffer.append("$(\"#"+formId+"\").attr('method', 'post');");
		buffer.append("$(\"#"+formId+"\").submit();");
		buffer.append("}");
		buffer.append("</script>");
		
		buffer.append("<input type=\"hidden\" id=\""+link+"\" name=\""+link+"\" />");
		
		int centerPage = pager.printPage / 2;
		int startPageNumber = pager.pageNo - centerPage;
		if ( startPageNumber < 0 ) {
			startPageNumber = 0;
		}
		
		int endPageNumber = startPageNumber + pager.printPage;
		if ( endPageNumber > pager.totalPage ) {
			endPageNumber = pager.totalPage;
		}
		
		if ( endPageNumber - startPageNumber < pager.printPage ) {
			startPageNumber = startPageNumber - (pager.printPage - (endPageNumber - startPageNumber));
			if( startPageNumber < 0 ) {
				startPageNumber = 0;
			}
		}
		
		String pageNumber = "";
		
		if ( startPageNumber > 0 ) {
			buffer.append("<a href=\"javascript:movePage('" + (pager.pageNo - 1) + "')\">" + prev + "</a>");
		}
		
		for (int i = startPageNumber; i < endPageNumber; i++) {
			pageNumber = pageFormat.replaceAll("@", (i+1) + "");
			if (i == pager.pageNo) {
				pageNumber = "<b>" + pageNumber + "</b>";
			}
			
			buffer.append("<a class='page' href=\"javascript:movePage('" + i + "')\">" + pageNumber + "</a>");
		}
		
		if ( pager.pageNo < endPageNumber-1 ) {
			buffer.append("<a href=\"javascript:movePage('" + (pager.pageNo + 1) + "')\">" + next + "</a>");
		}

		return buffer.toString();
	}
	
}
