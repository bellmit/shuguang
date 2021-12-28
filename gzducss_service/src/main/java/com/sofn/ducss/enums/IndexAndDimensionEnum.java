package com.sofn.ducss.enums;

import lombok.Getter;

public interface IndexAndDimensionEnum
{
    @Getter
	enum Index{
		//指标               							指标编号     指标中文名
		OUTPUT    									(1, "产生量"),
		UTILIZATION									(2, "利用量"),
		DIRECTRETURN								(3, "直接还田量"),
		CALLIN										(4, "市场主体调入量"),
        FIVEMATERIALS								(5, "五料化利用量"),
        OEE											(6, "综合利用率"),
		COMPREHENSIVEUTILIZATION					(7, "综合利用量"),
		CUCI										(8, "综合利用能力指数"),
		IUCI										(9, "产业化利用能力指数");

		private int number;
		private String name;

		Index(int number, String name) {
			this.number = number;
			this.name = name;
		}
	}

	@Getter
    enum Dimension{
        //维度              							     所属指标      中文名
        LSCL    									(1, "粮食产量"),
        CSL									        (1, "产生量"),
        KSJL								        (1, "可收集量"),
        DCL										    (1, "调出量"),

        SCZTLYL										(2, "市场主体利用量"),
        FSLYL					                    (2, "分散利用量"),

        DIRECTRETURN								(3, "直接还田量"),
        CALLIN										(4, "市场主体调入量"),

        FLHLYL										(5, "肥料化利用量"),
        SLHLYL										(5, "饲料化利用量"),
        RLHLYL										(5, "燃料化利用量"),
        JLHLYL										(5, "基料化利用量"),
        YLHLYL										(5, "原料化利用量"),

        OEE											(6, "综合利用率"),

        COMPREHENSIVEUTILIZATION					(7, "综合利用量"),

        CUCI										(8, "综合利用能力指数"),

        IUCI										(9, "产业化利用能力指数");


        private int number;
        private String name;

        Dimension(int number, String name) {
            this.number = number;
            this.name = name;
        }


    }




}
