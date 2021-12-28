-- 处理collect_flow 秸秆利用量数据
update collect_flow set
syn_utilize_num = round(straw_utilize_num * 100/collect_num,10)
where
abs(syn_utilize_num - round(straw_utilize_num * 100/collect_num,10)) > 0.001 and collect_num != 0 and year = '2020';

update collect_flow set straw_utilize_num = main_num + farmer_split_num + direct_return_num + export_num - buy_other_num
where main_num + farmer_split_num + direct_return_num + export_num - buy_other_num != straw_utilize_num;