package com.academicwork.blog;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;


    @RequestMapping(method = RequestMethod.GET, path = "/blog/")
    public ModelAndView listBlogs() {
        return new ModelAndView("blog/list")
                .addObject("blogs", blogRepository.listBlogs());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/blog/{blogId}/{pageId}")
    public ModelAndView listPosts(@PathVariable ("blogId") long blogId, @PathVariable ("pageId") long pageId) {
        System.out.println(blogId);
        System.out.println(pageId);
        Blog blog = blogRepository.getBlog(blogId);
        //long pageId = blogId/5;

        return new ModelAndView("blog/posts")
                .addObject("blog", blog)
                .addObject("author", blogRepository.getAuthorOf(blog))
                .addObject("posts", blogRepository.getEntriesIn(blog, pageId));

    }
/*    @RequestMapping(method = RequestMethod.POST, path = "blog/{blogId}/posts")
    public ModelAndView InsertPosts(@RequestParam String title, @PathVariable long blogId) {
        Blog blog = blogRepository.postBlog(title, blogId);
        return new ModelAndView("blog/posts/redir/{blogId}")

                .addObject("blog", blog)
                .addObject("author", blogRepository.getAuthorOf(blog))
                .addObject("posts", blogRepository.getEntriesIn(blog));

    }*/


@RequestMapping(method = RequestMethod.POST, path = "blog/{blogId}/addposts")
public String InsertPosts(HttpServletRequest request, @RequestParam String title, @PathVariable long blogId) {
    Blog blog = blogRepository.postBlog(title, blogId);
    String redirectUrl = request.getScheme() + "://localhost:8080/blog/{blogId}/";
    return "redirect:" + redirectUrl;

}

/*    @RequestMapping(method = RequestMethod.GET, path = "/blog/{blogId}")
    public ModelAndView listPostsafter(@PathVariable long blogId) {
        Blog blog = blogRepository.getBlog(blogId);
        return new ModelAndView("blog/posts")
                .addObject("blog", blog)
                .addObject("author", blogRepository.getAuthorOf(blog))
                .addObject("posts", blogRepository.getEntriesIn(blog));
    }*/





}
