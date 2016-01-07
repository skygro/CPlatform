/********************************************
esayui datagrid 表格数据分页
*********************************************/
	function pagerFilter(data) {
		if (typeof data.length == 'number' && typeof data.splice == 'function') { // is array
			data = {
				total : data.length,
				rows : data
			}
		}
		var dg = $(this);
		var opts = dg.datagrid('options');
		var pager = dg.datagrid('getPager');
		pager.pagination( {
			    pageList: [10,50,100],
			    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			    onSelectPage : function(pageNum, pageSize) {
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh', {
						pageNumber : pageNum,
						pageSize : pageSize
					});
					dg.datagrid({
						queryParams: {
							currentPage:pageNum,
							pageSize:pageSize
						}
	        		});
					dg.datagrid('loadData', data);
				},
			    onRefresh:function(pageNum,pageSize){  
			    	dg.datagrid('reload',{
						page:pageNum,
					    rows:pageSize
	        		}); 
			    },
			    onChangePageSize:function(pageNum){  
			    	dg.datagrid('reload',{
						page:pageNum
	        		}); 
			    }

		});
		if (!data.originalRows) {
			data.originalRows = (data.rows);
		}
		var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		return data;
	}