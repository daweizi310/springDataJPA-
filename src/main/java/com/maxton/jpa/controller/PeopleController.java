package com.maxton.jpa.controller;
import java.util.Map;

import com.maxton.jpa.pojo.People;
import com.maxton.jpa.service.PeopleService;
import com.maxton.jpa.util.PageResult;
import com.maxton.jpa.util.Result;
import com.maxton.jpa.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器层
 * @Author maxton.zhang
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",peopleService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",peopleService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<People> pageList = peopleService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<People>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",peopleService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param people
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody People people  ){
		peopleService.add(people);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param people
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody People people, @PathVariable String id ){
		people.setPid(id);
		peopleService.update(people);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		peopleService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
