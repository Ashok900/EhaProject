package com.elephant.contoller.customer1;




import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.elephant.constant.StatusCode;
import com.elephant.dao.ProductDao;
import com.elephant.model.ProductModel;
import com.elephant.model.ProductModel1;
import com.elephant.response.ErrorObject;
import com.elephant.response.Response;
import com.elephant.service.ProductService;
import com.elephant.utils.CommonUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@RestController
@RequestMapping("/v1")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService uploadproductservice;
	
	@Autowired
	ProductDao 	productDao;
	
	//------------------------------Add Products--------------------------------------
	@RequestMapping(value="/addproduct",method=RequestMethod.POST,produces="application/json" )
	public Response  addproduct(@Valid @RequestBody ProductModel model,@RequestParam(value="categoryName") String categoryName,HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) 
			throws Exception {
		return  (Response) uploadproductservice.addproduct(model,categoryName);
	}
	
	//------------------------------Filter Products--------------------------------------
//	@RequestMapping(value="/product/Filter",method=RequestMethod.GET,produces="application/json")
//	public @ResponseBody String  getProductByCatagory(String categoryName,String colors,Float discount,Double length,Double min,Double max, String materialType, 
//			String fabricPurity, String pattern,String border, String borderType,String zariType, String blouse,String blouseColor, Double blouseLength, HttpServletRequest request,HttpServletResponse responce)throws Exception{
//		logger.info("getProductByCatagory: Received request: " + request.getRequestURL().toString()
//				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
//		List<ProductModel> updatemodel = uploadproductservice.getProductByCatagory( categoryName,  colors, discount, length, price, materialType, fabricPurity,
//				  pattern, border,borderType,zariType,blouse,blouseColor,blouseLength);
//		Response res = CommonUtils.getResponseObject("List of product");
//		if (updatemodel.isEmpty()) {
//			ErrorObject err = CommonUtils.getErrorResponse("product Not Found", "product Not Found");
//			res.setErrors(err);
//			res.setStatus(StatusCode.ERROR.name());
//			res.setMessage("product not found");
//		} else {
//			res.setData(updatemodel);
//		}
//		logger.info("getProductByCatagory: Sent response");
//		return CommonUtils.getJson(res);
//	}
//	
	@RequestMapping(value="/product/Filter1",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody String  getProductByCatagory1(@RequestBody ProductModel1 pm1, HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByCatagory: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
//		List<ProductModel> updatemodel = uploadproductservice.getProductByCatagory( pm1.getCategoryName(),  pm1.getColors(), pm1.getDiscount(), pm1.getLength(), pm1.getMin(),pm1.getMax(), pm1.getMaterialType(), pm1.getFabricPurity(),
//				   pm1.getPattern(),pm1.getBorder(),pm1.getBorderType(),pm1.getZariType(),pm1.getBlouse(),pm1.getBlouseColor(),pm1.getBlouseLength());
		
		List<ProductModel> updatemodel = uploadproductservice.getProductByCatagory1(pm1);
		Response res = CommonUtils.getResponseObject("List of product");
		if (updatemodel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("product Not Found", "product Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("product not found");
		} else {
			res.setData(updatemodel);
		}
		logger.info("getProductByCatagory: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//------------------------------Update Products--------------------------------------
	@RequestMapping(value = "/updateproduct", method = RequestMethod.PUT, produces = "application/json")
	public Response updateProduct(@RequestBody ProductModel updateproduct, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("updateProduct: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("updateProduct: Received request: " + CommonUtils.getJson(updateproduct));
		return uploadproductservice.updateProduct(updateproduct);
	}
	
	//------------------------------Delete Product By Id--------------------------------------
	@RequestMapping(value = "/deleteproduct/{ProductId}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Response deleteproduct(@PathVariable("ProductId") String productId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("deleteproduct: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		return uploadproductservice.deleteproduct(productId);
	}
	
	//-------------------------------Product Soft Delete By Id-------------------------------------
	@RequestMapping(value="/deleteproduct/{productId}/{isActive}", method=RequestMethod.DELETE, produces="application/json")
	public @ResponseBody Response deleteProduct(@PathVariable("productId") String productId,@PathVariable boolean isActive, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		logger.info("deleteProduct: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("deleteProductId: Received request: " + CommonUtils.getJson(productId));
		return uploadproductservice.deleteProduct(productId,isActive);
	}

	//-------------------------------- Get Product By Id------------------------------------
	@RequestMapping(value="/productbyId/{productId}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductById(@PathVariable ("productId") String productId,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductById: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		ProductModel productModel = uploadproductservice.getProductById( productId);
		Response res = CommonUtils.getResponseObject("List of product");
		if (productModel == null) {
			ErrorObject err = CommonUtils.getErrorResponse("product Not Found", "product Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("product not found");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByCatagory: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//-------------------------------Upload Product By Excel Sheet-------------------------------------
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> importExcel(@RequestPart MultipartFile file,@RequestParam String categoryName,final HttpServletRequest request) {
		try {
			uploadproductservice.exportExcel(file,categoryName);
		} 
		catch (Exception e) {}
		return new ResponseEntity<String>("Successfully uploaded - " + file.getOriginalFilename(),
		new HttpHeaders(), HttpStatus.OK);
	}

	//-------------------------------- Get Product By Color------------------------------------
	@RequestMapping(value="/productbyColor/{colors}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByColor(@PathVariable ("colors") String colors,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductById: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByColor(colors);
		Response res = CommonUtils.getResponseObject("Products By Colour");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("Product Not Found", "Please choose another colour");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the selection.");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByCatagory: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Product By Discount------------------------------------	
	@RequestMapping(value="/productbyDiscount/{discount}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByDiscount(@PathVariable ("discount") float discount,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductById: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByDiscount(discount);
		Response res = CommonUtils.getResponseObject("List Of Product By Discount");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("Product Discout Not Found", "product Discount Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the selection.");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByCatagory: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get product By Occasion------------------------------------
	@RequestMapping(value="/productbyOccassion/{occassion}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByOccassion(@PathVariable ("occassion") String occassion,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByOccassion: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString()==null ) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByOccassion(occassion);
		Response res = CommonUtils.getResponseObject("List Of Product By Occassion");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByOccassion Not Found", "ProductByOccassion Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the selection.");
		} else {
			res.setData(productModel);
		}
		logger.info("ProductByOccassion: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products Within Rs.1000to3000 ------------------------------------
	@RequestMapping(value="/productbyPrice/{price-1000To3000}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByPrice(@PathVariable ("price-1000To3000") Double price,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByPrice: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByPrice(price);
		Response res = CommonUtils.getResponseObject("List of product 1000 to 3000");
		if (productModel == null) {
			ErrorObject err = CommonUtils.getErrorResponse("product price Not Found", "product Price Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("product price not found");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByPrice: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products With Min-Max Price------------------------------------
	@RequestMapping(value="/productbyPriceRange/{min-max}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByPriceRange(@RequestParam ("minPrice") double minPrice,@RequestParam ("maxPrice") double maxPrice,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByPrice: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByPriceRange(minPrice,maxPrice);
		Response res = CommonUtils.getResponseObject("List of product min to max");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("product price Not Found", "product Price Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("product price not found");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByPrice: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products with price Range By Category Name------------------------------------
	@RequestMapping(value="/productbyPriceRange/{categoryName}/{min-max}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductsByPriceRange(@RequestParam ("categoryName") String categoryName, @RequestParam ("minPrice") double minPrice,@RequestParam ("maxPrice") double maxPrice,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByPrice: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductsByPriceRange(categoryName,minPrice,maxPrice);
		Response res = CommonUtils.getResponseObject("List of product min to max");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("product price Not Found", "product Price Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("product price not found");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByPrice: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products By MeterialType------------------------------------
	@RequestMapping(value="/productbyMaterialType/{materialType}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByMaterialType(@PathVariable ("materialType") String materialType,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByMaterialType: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByMaterialType(materialType);
		Response res = CommonUtils.getResponseObject("List Of Product By MaterialType");
		if (productModel  .isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByMaterialType Not Found", "ProductByMaterialType Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the material type.");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByMaterialType: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products By Border------------------------------------
	@RequestMapping(value="/productByBorder/{border}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByBorder(@PathVariable ("border") String border,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByBorder(border);
		Response res = CommonUtils.getResponseObject("List of Product By Border");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByBorder Not Found", "ProductByBorder Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the border");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByBorder: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products By Border Types------------------------------------
	@RequestMapping(value="/productByBorderType/{borderType}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByBorderType(@PathVariable ("borderType") String borderType,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByBorderType(borderType);
		Response res = CommonUtils.getResponseObject("List Of Product By Border Type");
		if (productModel .isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByBorderType Not Found", "ProductByBorderType Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the border type");
		} else {
			res.setData(productModel);
		}
		
		logger.info("getProductByBorderType: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Product By ZariType------------------------------------
	@RequestMapping(value="/productByZariType/{zariType}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByZariType(@PathVariable ("zariType") String zariType,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByZariType(zariType);
		Response res = CommonUtils.getResponseObject("List Of Product By Zari Type");
		if (productModel .isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByZariType Not Found", "ProductByZariType Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the zari type");
		} else {
			res.setData(productModel);
		}
		
		logger.info("getProductByZariType: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Product By Blouse------------------------------------
	@RequestMapping(value="/productByBlouse/{blouse}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByBlouse(@PathVariable ("blouse") String blouse,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByBlouse(blouse);
		Response res = CommonUtils.getResponseObject("List of Product By Blouse");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByBlouse Not Found", "ProductByBlouse Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the blouse");
		} else {
			res.setData(productModel);
		}
		
		logger.info("getProductByBlouse: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products By Pattern------------------------------------
	@RequestMapping(value="/productByPattern/{pattern}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByPattern(@PathVariable ("pattern") String pattern,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByPattern(pattern);
		Response res = CommonUtils.getResponseObject("List Of Product By Pattern");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByPattern Not Found", "ProductByPattern Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the pattern.");
		} else {
			res.setData(productModel);
		}
		
		logger.info("ProductByPattern: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products By Fabric Purity------------------------------------
	@RequestMapping(value="/productbyFabricPurity/{fabricPurity}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByFabricPurity(@PathVariable ("fabricPurity") String fabricPurity,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByFabricPurity(fabricPurity);
		Response res = CommonUtils.getResponseObject("List Of Product By FabricPurity");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByFabricPurity Not Found", "ProductByFabricPurity Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the fabric purity.");
		} else {
			res.setData(productModel);
		}
		
		logger.info("ProductByFabricPurity: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Product By Blouse Color------------------------------------
	@RequestMapping(value="/productByBlouseColor/{blouseColor}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByBlouseColor(@PathVariable ("blouseColor") String blouseColor,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByBlouseColor(blouseColor);
		Response res = CommonUtils.getResponseObject("List of Product By BlouseColor");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByBlouseColor Not Found", "ProductByBlouseColor Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the blouseColor.");
		} else {
			res.setData(productModel);
		}
		
		logger.info("ProductByBlouseColor: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Product By Blouse Length------------------------------------
	@RequestMapping(value="/productByBlouseLength/{blouseLength}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByBlouseLength(@PathVariable ("blouseLength") double blouseLength,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByBlouseLength(blouseLength);
		Response res = CommonUtils.getResponseObject("List of Product By BlouseLength");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductByBlouseLength Not Found", "ProductByBlouseLength Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the BlouseLength.");
		} else {
			res.setData(productModel);
		}
		
		logger.info("ProductByBlouseLength: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products By Saree Border------------------------------------
	@RequestMapping(value="/productBySareeLength/{length}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductBySareeLength(@PathVariable ("length") Double length,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductByBorder: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductBySareeLength(length);
		Response res = CommonUtils.getResponseObject("List Of Product By Saree Length");
		if (productModel .isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("ProductBySareeLength Not Found", "ProductBySareeLength Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the saree length.");
		} else {
			res.setData(productModel);
		}
		
		logger.info("ProductBySareeLength: Sent response");
		return CommonUtils.getJson(res);
	}

	//--------------------------------Get All Products------------------------------------
	@RequestMapping(value="/product/getall", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String getProducts(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("getProducts: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel>model=uploadproductservice.getProducts();
		Response res = CommonUtils.getResponseObject("ALL Products Details");
	
		if (model.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("Products Not Found", "Products Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products.");
		} else {
			res.setData(model);
		}
		logger.info("Product: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Discounts By filter------------------------------------
	@RequestMapping(value="/productbyFilterDiscount/{discount}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByFilterDiscount(@PathVariable ("discount") float discount,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductById: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByFilterDiscount(discount);
		Response res = CommonUtils.getResponseObject("List Of Product By Filter Discount");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("Product Discout Not Found", "product Discount Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the selection.");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductByCatagory: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Sorting Price LowToHigh------------------------------------
	@RequestMapping(value="/product/PriceSortingLowToHigh", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String getPriceSortingLowToHigh(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("getPriceSortingLowToHigh: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel>model=uploadproductservice.getPriceSortingLowToHigh();
		Response res = CommonUtils.getResponseObject("ALL Products PriceSortingLowToHigh Details");
	
		if (model.isEmpty()) {
			
			ErrorObject err = CommonUtils.getErrorResponse("Products Not Found", "Products Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products.");
		} else {
			
			res.setData(model);
		}
		logger.info("Product: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Price Sorting HighToLow------------------------------------
	@RequestMapping(value="/product/PriceSortingHighToLow", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String getPriceSortingHighToLow(HttpServletRequest request, HttpServletResponse response)throws Exception{
		logger.info("getPriceSortingLowToHigh: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel>model=uploadproductservice.getPriceSortingHighToLow();
		Response res = CommonUtils.getResponseObject("ALL Products Price Sorting HighToLow Details");
	
		if (model.isEmpty()) {
			
			ErrorObject err = CommonUtils.getErrorResponse("Products Not Found", "Products Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products.");
		} else {
			res.setData(model);
		}
		logger.info("Product: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Price Sorting HighToLow By CategoryName------------------------------------
	@RequestMapping(value="/productbyTypesSortingPriceHighToLow/{categoryName}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByTypesSortingPriceHighToLow(@RequestParam (value="categoryName") String types,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductById: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByTypesSortingPriceHighToLow(types);
		Response res = CommonUtils.getResponseObject("List Of Products By Sorting Price High To Low For CategoryName");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("Product Not Found", "Product Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the selection.");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductBycategoryNameSortingPriceHighToLow: Sent response");
		return CommonUtils.getJson(res);
	}

	//--------------------------------Price Sorting LowToHigh By CategoryName------------------------------------
	@RequestMapping(value="/productbyCategoryNameSortingPriceLowToHigh/{categoryName}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductByTypesSortingPriceLowToHigh(@PathVariable ("categoryName") String types,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProduct: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		List<ProductModel> productModel = uploadproductservice.getProductByTypesSortingPriceLowToHigh(types);
		Response res = CommonUtils.getResponseObject("List Of Products By Sorting Price Low To High For CategoryName");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("Product Not Found", "product Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("There are no products matching the selection.");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductBycategoryNameSortingPriceLowToHigh: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Get Products By CategoryId------------------------------------
	@RequestMapping(value="/ProductBycategoryId/{categoryId}",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody String  getProductBycategoryId(@PathVariable ("categoryId") String categoryId,HttpServletRequest request,HttpServletResponse responce)throws Exception{
		logger.info("getProductById: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		
		List<ProductModel> productModel = uploadproductservice.getProductBycategoryId( categoryId);
		Response res = CommonUtils.getResponseObject("List of product");
		if (productModel.isEmpty()) {
			ErrorObject err = CommonUtils.getErrorResponse("product Not Found", "product Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage("product not found");
		} else {
			res.setData(productModel);
		}
		logger.info("getProductBycategoryId: Sent response");
		return CommonUtils.getJson(res);
	}
	
	//--------------------------------Excel Sheet Download------------------------------------
	@RequestMapping(path = "/download_excel", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download() throws IOException {
try {
	ByteArrayResource resource = uploadproductservice.exportExcelHeaders("aaa");

	return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Template.xlsx")
	        .contentLength(resource.contentLength())
	        //.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(resource);
	}catch (Exception e) {
		// TODO: handle exception
	}
return null;
	}
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = {"/download/excel-report"},
	                method = RequestMethod.GET,produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public HttpEntity<byte[]> downloadExcelReport() throws IOException {
	 
	    byte[] excelContent = getReportContent();
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	    header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.xls");
	    header.setContentLength(excelContent.length);
	 
	    return new HttpEntity<byte[]>(excelContent, header);
	}

	@SuppressWarnings("resource")
	private byte[] getReportContent() throws IOException {
		String[] columnshead = {"sku","quantity","saree_length","pattern","fabric_purity","border","border_type","zari_type","material_type","price", "discount", "blouse","blouse_color","blouse_length","saree_colors","collection_desc","occassion","main_imageUrl","otherImageUrls"};
		// List<UploadProductDomain> domain =  new ArrayList<>();
		Workbook workbook = new XSSFWorkbook();
		 //CreationHelper createHelper = workbook.getCreationHelper();
		XSSFSheet  sheet = (XSSFSheet) workbook.createSheet("Product_Upload_Template");
		
		 
		 Font headerFont = workbook.createFont();
	        headerFont.setBold(true);
	        headerFont.setFontHeightInPoints((short) 14);
	        headerFont.setColor(IndexedColors.RED.getIndex());
	        
	     CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFont(headerFont);
	        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        headerCellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
	       // headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);	       
	        headerCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
	        
	        Row headerRow = sheet.createRow(0);
	       
	        
	        for(int i = 0; i < columnshead.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columnshead[i]);
	            cell.setCellStyle(headerCellStyle);
	        }
	        
	        for(int i = 0; i < columnshead.length; i++) {
	            sheet.autoSizeColumn(i);
	        }
	       
			//String d="G:\\Mahesh\\Template.xlsx";
	       // FileOutputStream fileOut = new FileOutputStream("Template.xlsx");
	       // workbook.write(fileOut);
	       // fileOut.close();
	        //workbook.close();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        workbook.write(baos);
	     
	        return baos.toByteArray();
}
	
	
	@RequestMapping(value = "/files", method = RequestMethod.GET,produces="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public void getFile(
			@RequestParam(value="file_name", required = false) String fileName, 
	    HttpServletResponse response) {
	    try {
	      // get your file as InputStream
	      InputStream is = uploadproductservice.exportExcel();
	      // copy it to response's OutputStream
	      IOUtils.copy(is, response.getOutputStream());
	      response.setHeader("Content-Disposition", "attachment; filename=file.xlsx");
	      response.setContentType("text/plain; charset=utf-8");
	      response.flushBuffer();
	    } catch (IOException ex) {
	      logger.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
	      throw new RuntimeException("IOError writing file to output stream");
	    }

	}
	
	
}

