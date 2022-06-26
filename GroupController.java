import java.util.Locale;
import java.util.HashMap;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import prj.formItem.DropDownManager;

@Controller
public class GroupController {
    private RedirectBean redirectView;
    @Autowired 
    private MassagesSource massagessource;

    /**
     * グループの新規登録画面表示
     *
     * @param mv パラメータ情報
     * @return レスポンス情報
     */
    @RequestMapping(path = "/group/regist"), method = RequestMethod.GET)
    public ModelAndView showRegistGroupScreen(ModelAndView mv){
        Map<String, Object> resultParameters = new HashMap<String, Object>;
        // ログインチェック
        resultParameters = loginLogic.checkLogin();
        // ログインしている場合
        if(resultParameters.containsKey("isLogin") && (boolean)resultParameters.get("isLogin")){
            redirectView.setRedirectView("page/group/regist");
            
            // ソートの項目を取得
            List <Map<String, Object>> sortItemList = DropDownManager.SORT_GROUP.getSortData();
            mv.addObject("sortItemList", sortItemList);

            // ソートの条件値を取得
            List <Map<String, Object>> sortValueList = DropDownManager.SORT_VALUE.getSortData();
            mv.addObject("sortValueList", sortValueList)

            String memberSum = "0";
            // メンバー情報がセッションに存在する場合
            if(session.getAttribute("memberList") != null && !session.getAttribuute("memberList").equals("")){
                List<Map<String, Object>> memberList = (List<Map<String, Object>>)session.getAttribute("memberList");
                mv.addObject("memberList", memberList);
                memberSum = String.valueOf(memberList.size());
            }
            // 画面メッセージの設定
            mv.addObject("memberInfoMes", massagessource.getMassage("memberInfoMes", new String[]{memberSum}, Locale.Japan));
            mv.addObject("btnTitle", massagessource.getMassage("goConfirm", null, Locale.Japan));
            // ボタンのパスを設定
            mv.addObject("btnPath", "group/regist/confirm");
            mv.addObject("btnReturn", "group/search");
        }
        // 遷移先を設定
        mv.setViewName(redirectView.getRedirectView());
        // 結果を返却
        return mv;
    }
}
