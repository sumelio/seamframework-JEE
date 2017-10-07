package com.leasing.popular.wf.generico.view.validator;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;




public class Validar extends ValidarGeneric implements Validator {

	private boolean plus4Required;
	private boolean plus4Optional;

	public Iterator getAttributeNames() {
		return null;
	}

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String[] name = value.toString().split(" ");

		//System.out.println("en validar " + component.getId());
		String entered = (String)value;

		if (component.getId().equals("username") && name.length < 1) {
			throw new ValidatorException(
					mensajeError(resourceBundle.getString("login_usuario")));
		}else

			if (component.getId().equals("userpasswordLogin") && name.length < 1) {
				throw new ValidatorException(
						mensajeError(resourceBundle.getString("login_contrasena")));
			}else
				if (component.getId().equals("perCorreo") && entered.length() > 0 ) {

					//System.out.println("enteredEmail " + entered);

					//Set the email pattern string
					Pattern p = Pattern.compile(".+@.+\\.[a-zA-Z]+");

					//Match the given string with the pattern
					Matcher m = p.matcher(entered);

					//Check whether match is found
					boolean matchFound = m.matches();

					if (entered != null && entered.trim().length()> 0 && !matchFound) {

						throw new ValidatorException(
								mensajeError(resourceBundle.getString("format_email")));
					}
				}/*else
					if (component.getId().equals("perUrl") && entered.length() > 0 ) {

						System.out.println("Burl " + entered);

						//Set the email pattern string
						Pattern p = Pattern.compile("www+.+[A-Za-z0-9]+.+[A-Za-z]");

						//Match the given string with the pattern
						Matcher m = p.matcher(entered.trim().toLowerCase());

						//Check whether match is found
						boolean matchFound = m.matches();

						if (!matchFound) {

							throw new ValidatorException(
									mensajeError(resourceBundle.getString("format_web")));
						}
					}*//*else
						if(component.getId().equals("perNombres")){
							System.out.println("perNombres " + entered.trim());

							//Set the email pattern string
							Pattern p = Pattern.compile("[a-z·ÈÌÛ˙ÒA-Z¡…Õ”⁄]+\\s?+[a-z·ÈÌÛ˙ÒA-Z¡…Õ”⁄]");

							//Match the given string with the pattern
							Matcher m = p.matcher(entered.trim().toLowerCase());

							//Check whether match is found
							boolean matchFound = m.matches();

							if (!matchFound) {

								throw new ValidatorException(
										mensajeError("El valor no es validor"));
							}
						}*/



	}

}