<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class="topblock">
		<p>Coffee Bean</p>
	</div>
	<div align="center" class="row">
		<c:forEach items="${itemList }" var="item">
		 <div class="col-lg-3 ">
		  <div class="thumbnail">
				
				<a href="../detail/detail.html?itemNo=${item.itemNo}"><img src="${item.photo}" style="max-height: 300px; max-width: 600px"></a>
					<p>${item.itemName }</p>
					<p>${item.price }원</p>
					<p>
					<fmt:formatDate value="${item.roastingDate}" pattern="yyyy-MM-dd" />
				</p>
				</div>
			</div>
		</c:forEach>
	</div>