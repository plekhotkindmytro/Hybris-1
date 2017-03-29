<%@tag import="org.apache.log4j.Logger" %>
<%@tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="orderData" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<script>
	yotpoTrackConversionData = {
								orderId: "${orderData.code}", 
								orderAmount: "${orderData.totalPrice.value}", 
								orderCurrency: "${orderData.totalPrice.currencyIso}"
								}
</script>
<noscript>
	<img src="${yotpoConversionURL}&order_id=${orderData.code}&order_amount=${orderData.totalPrice.value}&order_currency=${orderData.totalPrice.currencyIso}" 
				width="1" height="1">
</noscript>