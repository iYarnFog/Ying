/*
 * Javascript base64encode() base64加密函数
 * @param string input 原始字符串
 * @return string 加密后的base64字符串
*/
function base64Encode(input) {
	var rv;
	rv = encodeURIComponent(input);
	rv = unescape(rv);
	rv = window.btoa(rv);
	return rv;
}

/*
 * Javascript base64Decode() base64解密函数
 * @param string input base64加密字符串
 * @return string 解密后的字符串
*/
function base64Decode(input) {
	rv = window.atob(input);
	rv = escape(rv);
	rv = decodeURIComponent(rv);
	return rv;
}