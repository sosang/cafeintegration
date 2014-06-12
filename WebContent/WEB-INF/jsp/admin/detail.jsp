<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세 화면</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>상품 상세 화면</h2>
		<table>
			<tr>
				<td><img src="${toViewImage }"></td>
				<td align="center">
					<table>
						<tr height="50">
							<td width="80">상품명</td>
							<td width="160">${item.itemName}</td>
						</tr>
						<tr height="50">
							<td width="80">원산지</td>
							<td width="160">${item.origin }</td>
						</tr>
						<tr height="50">
							<td width="80">가격</td>
							<td width="160">${item.price}원</td>
						</tr>
						<tr height="50">
							<td width="80">비고</td>
							<td width="160">${item.itemInfo}</td>
						</tr>
						<tr height="50">
							<td width="80">로스팅정도</td>
							<td width="160">${item.roastingLevel }</td>
						</tr>
						<tr height="50">
							<td width="80">로스팅날짜</td>
							<td width="160">${item.roastingDate }</td>
						</tr>
						<tr height="50">
							<td width="80">프로세싱</td>
							<td width="160">${item.processing}</td>
						</tr>
						<tr height="50">
							<td width="80">등급</td>
							<td width="160">${item.grade}</td>
						</tr>
						<tr height="50">
							<td width="80">총수량</td>
							<td width="160">${item.totalProduct}</td>
						</tr>
						<tr>
							<td colspan="2" align="center" width="240">
								<a href="../admin/itemList.html">ㅁ 상품목록으로 돌아가기</a>/
								<a href="../admin/itemEdit.html?itemNo=${item.itemNo}">수정</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>