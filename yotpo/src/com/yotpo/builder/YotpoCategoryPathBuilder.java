package com.yotpo.builder;

import de.hybris.platform.catalog.model.classification.ClassificationClassModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;


/**
 * This class is used to build category path of product
 */
public class YotpoCategoryPathBuilder
{
	@Resource
	private ProductService productService;

	/**
	 * Build and returns the category path of specified prodcut.
	 *
	 * @param productCode
	 * @return Category path
	 */
	public String buildCategoryPath(final String productCode)
	{
		final StringBuilder categoryPath = new StringBuilder("");
		String categoryPathSeprator = "";
		final ProductModel productModel = productService.getProductForCode(productCode);
		final List<String> categories = new ArrayList<String>();

		final Collection<CategoryModel> categoryModels = new ArrayList<CategoryModel>();

		final ProductModel baseProductModel = getBaseProduct(productModel);
		categoryModels.addAll(baseProductModel.getSupercategories());

		while (!categoryModels.isEmpty())
		{
			CategoryModel toDisplay = null;
			toDisplay = processCategoryModels(categoryModels, toDisplay);
			categoryModels.clear();
			if (toDisplay != null)
			{
				categories.add(toDisplay.getName());
				categoryModels.addAll(toDisplay.getSupercategories());
			}
		}
		Collections.reverse(categories);

		for (final String name : categories)
		{
			categoryPath.append(categoryPathSeprator);
			categoryPath.append(name);
			categoryPathSeprator = " > ";
		}

		return categoryPath.toString();
	}

	/**
	 * returns base product of variation product.
	 *
	 * @param product
	 * @return {@link ProductModel}
	 */
	private ProductModel getBaseProduct(final ProductModel product)
	{
		if (product instanceof VariantProductModel)
		{
			return getBaseProduct(((VariantProductModel) product).getBaseProduct());
		}
		return product;
	}

	/**
	 * process the Category Models based on ClassificationClassModel
	 *
	 * @param toDisplay
	 * @param categoryModels
	 * @return {@link CategoryModel}
	 */
	private CategoryModel processCategoryModels(final Collection<CategoryModel> categoryModels, final CategoryModel toDisplay)
	{
		CategoryModel categoryToDisplay = toDisplay;

		for (final CategoryModel categoryModel : categoryModels)
		{
			if (!(categoryModel instanceof ClassificationClassModel))
			{
				if (categoryToDisplay == null)
				{
					categoryToDisplay = categoryModel;
				}
			}
		}
		return categoryToDisplay;
	}
}
