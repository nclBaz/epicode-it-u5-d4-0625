package riccardogulin.u5d4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import riccardogulin.u5d4.entities.BlogPost;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.repositories.BlogsRepository;

import java.util.List;

@Service
@Slf4j
public class BlogsService {

	private final BlogsRepository blogsRepository;
	private final UsersService usersService;

	public BlogsService(BlogsRepository blogsRepository, UsersService usersService) {
		this.blogsRepository = blogsRepository;
		this.usersService = usersService;
	}

	public List<BlogPost> findAll() {
		return this.blogsRepository.findAll();
	}

	public void saveNewBlog(long userId, String title, String content) {
		User author = this.usersService.findById(userId);
		// TODO: Fare controlli su titolo e contenuto
		BlogPost newBlog = new BlogPost(title, content, author);
		this.blogsRepository.save(newBlog);
		log.info("Il blog " + newBlog.getTitle() + " è stato salvato correttamente");
	}
}
