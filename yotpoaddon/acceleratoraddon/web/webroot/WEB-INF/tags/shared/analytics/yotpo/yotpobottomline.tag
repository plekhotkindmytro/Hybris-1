<%@tag import="org.apache.log4j.Logger" %>
<%@tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="productData" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>

<c:if test="${productData != null}">
	<c:if test="${isBottomLineEnabled}">
		<c:forEach items="${productData.images}" var="image" begin="0" end="0">
			<c:set var="productImageURL" value="${image.url}" />
		</c:forEach>
		<div id="yotpo-bottomline-top-div" class="yotpo bottomLine"
		    data-appkey="${yotpoAppKey}"
		    data-domain="${baseUrl}"
		    data-product-id="${productData.code}"
		    data-product-models="${productData.baseProduct}"
		    data-name="${productData.name}"
		    data-url="${productData.url}"
		    data-image-url="${productImageURL}"
		    data-description="${productData.summary}"
		    data-bread-crumbs="${categoryPath}">
		</div> 
	</c:if>
</c:if>
