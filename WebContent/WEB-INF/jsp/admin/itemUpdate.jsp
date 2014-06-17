<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<style type="text/css">
a#coco{
	background-color: buttonface;
}
</style>
<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<script type="text/javascript">
window.onload=function(){
    CKEDITOR.replace('content1',{enterMode:'2',shiftEnterMode:'3',language:'ko',
    	toolbar:[['Bold','Italic','Underline','Strike','-','Subscript','Superscript','-','TextColor','BGColor','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Link','Unlink','-','Find','Replace','SelectAll','RemoveFormat','-','Table','SpecialChar'],'/',['Source','-','ShowBlocks','-','Font','FontSize','Undo','Redo','-','About']]
    
    });
};
</script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>상품 수정 화면</h2>
<form name="fileForm" method="post" enctype="multipart/form-data" action="../admin/itemUpdate.html"> 
		<table>
			<tr>
				<td>사진 : <input type="file" name="filePath"></td>
				<td align="center">
					<table>
						<tr height="50">
							<td width="80">상품명</td>
							<td width="160"><input type="text" name="itemName" size="115" value="${item.itemName}"></td>
						</tr>
						<tr height="50">
							<td width="80">원산지</td>
							<td width="160"><input type="text" name="origin" size="115" value="${item.origin }"></td>
						</tr>
						<tr height="50">
							<td width="80">가격</td>
							<td width="160"><input type="text" name="price" size="115" value="${item.price}"></td>
						</tr>
						<tr height="50">
							<td width="80">비고</td>
							<td width="160"><input type="text" id="content1" name="itemInfo" size="115" value="${item.itemInfo}"></td>
						</tr>
						<tr height="50">
							<td width="80">로스팅정도</td>
							<td width="160"><input type="text" name="roastingLevel" size="115" value="${item.roastingLevel }"></td>
						</tr>
						<tr height="50">
							<td width="80">등급</td>
							<td width="160"><input type="text" name="grade" size="115" value="${item.grade}"></td>
						</tr>
						<tr height="50">
							<td width="80">프로세싱</td>
							<td width="160"><input type="text" name="processing" size="115" value="${item.processing}"></td>
						</tr>
						<tr height="50">
							<td width="80">총 수량(kg)</td>
							<td width="160"><input type="text" name="totalProduct" size="115" value="${item.totalProduct}"></td>
						</tr>
						<tr height="50">
							<td width="80" colspan="2" align="center"><input type="submit" value="등록하기" class="btn" id="coco"/></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="itemNo" value="${item.itemNo }"/>
</form>
	</div>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>