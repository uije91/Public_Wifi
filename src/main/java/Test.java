import dao.MemberService;
import dao.WifiDAO;
import dto.WifiDTO;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        WifiDAO dao = new WifiDAO();
        String mgrNo = "GR010013";
        List<WifiDTO> list = dao.selectWifiList(mgrNo);

        for(WifiDTO wifi : list){
            System.out.println(wifi.getLat());
        }
    }
}
