package com.ignacode.core.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ignacode.core.model.Conexion;
import com.ignacode.core.model.Usuario;

@Component
public class CreandoConexion {
	@Bean(name = "beanUsuario")
	public Usuario getUsuario() {
		return new Usuario();
	}
	
	@Bean(name ="beanConexion")
	public Conexion getConexion() {
		Conexion conexion = new Conexion();
		conexion.setDb("mysql");
		conexion.setUrl("localhost");
		return conexion;
	}

}
