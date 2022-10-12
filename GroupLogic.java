/**
 * 更新対象にフラグを設定する
 *
 * @param valueList                入力値
 * @param updateTargetValueList    更新対象の値
 *
 */
public void setUpdateTargetFlag (List<Map<String, Object>> valueList,  List<Map<String, Object>> updateTargetValueList){
    Map<String, Integer> updateTarget = new HashMap<String, Integer>();
    List<Map<String, Integer>> updateList = new ArrayList<Map<String, Integer>>();
    int loopCount = 1;
    // 入力値と取得した更新対象の値を突合する
    for(Map<String,Object> valueMap : valueList){
        for(Map.Entry<String, Object> valueEntry : valueMap){
            updateTarget = new HashMap<String, Integer>();
            for(Map<String,Object> updateTarget : updateTargetValueList) {
            　　// 入力値と取得した更新対象の値が一致した場合、リストに格納する
                if(valueMap.get("id").equals(updateTarget.get("id")
                        && valueEntry.getValue().equals(updateTarget.get(valueEntry.getKey()))){
                    updateTarget.put(valueEntry.getKey(), loopCount);
                    updateList.add(updateTarget);
                }
            }
        }
    }
    // 更新対象の背景色を変更するためのフラグを設定する
    for(Map<String,Object> updateValue : updateList){
        for(Map.Entry<String, Object> updateValueEntry : updateValue){
            valueList.get(updateValueEntry.getValue()-1).put(updateValueEntry.getKey() + "Update", "true");
        }
    }
}
