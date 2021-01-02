package cn.yuc.jip4fg_server.job.enums;

/**
 * @author Yuc
 * @Classname WorkNatureEnum
 * @Date 2020/12/5 1:30
 */
public enum WorkNatureEnum {

    /* 实习 */
    FIELDWORK(0),
    /* 校招 */
    CAMPUS(1),
    /* 社招 */
    SOCIAL(2);

    private int workNature;

    private WorkNatureEnum(int workNature){
        this.workNature = workNature;
    }

    public static String getWorkNatureDesc(int workNatureCode) {
        for(WorkNatureEnum workNatureEnum:WorkNatureEnum.values()){
            if(workNatureEnum.workNature == workNatureCode){
                return getWorkNatureDesc(workNatureEnum);
            }
        }
        return "未知";
    }


    public static String getWorkNatureDesc(WorkNatureEnum workNatureEnum) {
        switch (workNatureEnum) {
            case FIELDWORK: return "实习";
            case CAMPUS: return "校招";
            case SOCIAL: return "社招";
            default: return "未知";
        }
    }
}
