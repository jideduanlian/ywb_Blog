import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ywb.domain.entity.User;
import com.ywb.domain.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class test {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testUserMapper(){
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,"sg");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }
}
