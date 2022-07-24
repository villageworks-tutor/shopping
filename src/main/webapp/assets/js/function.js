/**
 * 共通関数
 */
function createOptions(upper, selected = -1) {
	for (i = 0; i < upper; i++) {
		if (i + 1 == selected) {
			document.write("<option value\"" + (i + 1) + "\" selected>" + (i + 1) + "</option>");
		} else {
			document.write("<option value\"" + (i + 1) + "\">" + (i + 1) + "</option>");
		}
	}
}
