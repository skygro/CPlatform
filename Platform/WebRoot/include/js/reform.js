/********************************************
esayui datagrid 表格数据分页
*********************************************/
var formObj = document.forms;
for (var i=0; i<formObj.length; i++)
{
    var element = formObj[i];
    if (element.type == 'submit') { continue; }
    if (element.type == 'reset') { continue; }
    if (element.type == 'button') { continue; }
    if (element.type == 'hidden') { continue; }
 
        if (element.type == 'text') { element.value = ''; }
    if (element.type == 'textarea') { element.value = ''; }
    if (element.type == 'checkbox') { element.checked = false; }
    if (element.type == 'radio') { element.checked = false; }
    if (element.type == 'select-multiple') { element.selectedIndex = -1; }
    if (element.type == 'select-one') { element.selectedIndex = -1; }
}