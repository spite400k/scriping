package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hello.User;
import hello.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	
	@RequestMapping(path="/add" , method = { RequestMethod.GET, RequestMethod.POST })// Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	/***
	 * テーブル件数を返却する
	 * @return
	 */
	@GetMapping(path="/count")
	public @ResponseBody long getCount() {
		return userRepository.count();
	}
	
	/***
	 * ユーザー名で検索する
	 * @return
	 */
	@GetMapping(path="/user-by-name")
	public @ResponseBody Iterable<User> getUserByName(@RequestParam String name) {
		return userRepository.findByNameOrderByIdDesc(name);
	}
	
	/***
	 * ユーザー情報を更新する
	 * @param id
	 * @param name
	 * @param email
	 * @return
	 */
	@Transactional
	@GetMapping(path="/update-user")
	public @ResponseBody User updateUser (
			@RequestParam String id,
			@RequestParam String name,
			@RequestParam String email) {
		
	 
		Optional<User> optionalUser = userRepository.findById(Integer.valueOf(id));
		User userEntity = optionalUser.orElseThrow(IllegalStateException::new);
		
		userEntity.setName(name);
		userEntity.setEmail(email);
	 
		userEntity = userRepository.save(userEntity);
		return userEntity;
	}
	
	/***
	 * ハローページを出力する
	 * 
	 * @return
	 */
	@GetMapping(path="/")
	public ModelAndView getHello(ModelAndView mav) {
		
		String messageString = "こんにちは！タグボートを使ってみてね！";
 
		mav.addObject("message", messageString);
		
		// 全件抽出
		List<User> userEntityList = (List<User>) userRepository.findAll();
		mav.addObject("users",userEntityList);
		
		mav.setViewName("hello.html");
		
		return mav;
	}
}
