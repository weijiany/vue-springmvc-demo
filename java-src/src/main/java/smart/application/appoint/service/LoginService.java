package smart.application.appoint.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import smart.application.appoint.models.People;
import smart.application.appoint.util.StaticUtil;

@Service
public class LoginService {

    /**
     * 登录系统
     * @param people 封装前端的数据的 people
     * @return 成功、失败
     */
    public String login (People people) {
        UsernamePasswordToken token = new UsernamePasswordToken(people.getUsername(), people.getPassword());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        try {
            subject.login(token);
            session.setTimeout(-1000);
            People dbPeople = (People) subject.getPrincipal();
            session.setAttribute("user", dbPeople);
            return (String) session.getId();
        } catch (UnknownAccountException e) {
            // 账号不正确
            e.printStackTrace();
            return StaticUtil.USERNAME_NOT_FOUND;
        } catch (IncorrectCredentialsException e) {
            // 密码不正确
            e.printStackTrace();
            return StaticUtil.PASSWORD_ERROR;
        }
    }

    /**
     * 退出系统
     * @return 成功、失败
     */
    public String logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return StaticUtil.LOGOUT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return StaticUtil.LOGOUT_FAIL;
        }
    }
}
