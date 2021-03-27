import github.zhp.spring.three.mybatis.MybatisPlusGenOffice;

public class GenerationTest {
    public static void main(String[] args) {
        MybatisPlusGenOffice gen = new MybatisPlusGenOffice();
        gen.setAuthor("zhp");
        gen.setDbUrl("jdbc:mysql://localhost:3306/video_manage?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
        gen.setDbUserName("root");
        gen.setDbUserPassword("123456");
        gen.setDriverName("com.mysql.cj.jdbc.Driver");
        gen.setParentPackageName("video.manage.mode");
        gen.begin();
        // review_info,role_info,user_base_info,user_login,video_base_info,video_privilege,video_review,video_storage_info
    }
}
