package Musaib.MybackendProject.Services.impl;

import Musaib.MybackendProject.Exceptions.ResourceNotFoundException;
import Musaib.MybackendProject.Models.Category;
import Musaib.MybackendProject.Payloads.CategoryDto;
import Musaib.MybackendProject.Repositories.CategoryRep;
import Musaib.MybackendProject.Services.CategoryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@NoArgsConstructor
@Getter
@Setter


public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRep rep;
    @Autowired
    ModelMapper mapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        return  this.toDtoCategory(rep.save(this.toCategory(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat=this.rep.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

         cat.setCategoryTitle(categoryDto.getCategoryTitle());
         cat.setCategoryDescription(categoryDto.getCategoryDescription());
          CategoryDto us=this.toDtoCategory(this.rep.save(cat));

        return  us;
    }
    @Override
    public void deleteCategory(Integer categoryId) {
        this.rep.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
         this.rep.deleteById(categoryId);

    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
         CategoryDto us= this.toDtoCategory(this.rep.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId)));
        return us;
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<CategoryDto> list =this.rep.findAll().stream().map(item->toDtoCategory(item)).collect(Collectors.toList());
        return list;
    }
    public  CategoryDto toDtoCategory(Category category){


        return this.mapper.map(category,CategoryDto.class);

    }
    public  Category toCategory(CategoryDto categoryDto){


        return  this.mapper.map(categoryDto,Category.class);
    }
}
