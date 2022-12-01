package Musaib.MybackendProject.Controllers;

import Musaib.MybackendProject.Payloads.CategoryDto;
import Musaib.MybackendProject.Services.CategoryService;
import Musaib.MybackendProject.Services.impl.CategoryServiceImpl;
import Musaib.MybackendProject.Utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")

public class CategoryControllers {
    @Autowired
    CategoryServiceImpl categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){

       CategoryDto us=this.categoryService.createCategory(categoryDto);
        return  new ResponseEntity<CategoryDto>(us, HttpStatus.CREATED);


    }
    @PutMapping ("/{categoryId}")
    public ResponseEntity<CategoryDto>update(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable ("categoryId") Integer categoryId){
        CategoryDto us=this.categoryService.updateCategory(categoryDto,categoryId);

        return   new ResponseEntity<>(us,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAll(){
        return  new ResponseEntity<>(categoryService.getAllCategory(),HttpStatus.OK);
    }
    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryID") Integer categoryId){
         CategoryDto us=this.categoryService.getCategoryById(categoryId);
         return new   ResponseEntity<CategoryDto>(us,HttpStatus.OK);
    }
    @DeleteMapping("/{categoryID}")
    public  ResponseEntity<?> deleteById(@PathVariable("categoryID") Integer categoryId){
            this.categoryService.deleteCategory(categoryId);

            return  new ResponseEntity<>(new ApiResponse("deleted Successfully",true),HttpStatus.OK);
    }

}
