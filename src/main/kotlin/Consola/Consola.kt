package org.example.Consola

class Consola {

    private fun mostrarMensaje(msg: String){
        println(msg)
    }

    fun modoEjecucion(){
        mostrarMensaje("\nSeleccione modo de ejecución:\n\n"+
                "\t 1.SIMULACION (sólo en memoria)\n\n"+
                "\t 2.ALMACENAMIENTO (usar ficheros)\n"
        )
    }

    fun mostrarMenuAdmin(){
        mostrarMensaje("\n1. Usuarios\n"+
            "2. Seguros\n"+
            "3. Salir\n"
        )
    }

    fun submenuUsersAdmin(){
        mostrarMensaje("\n1. Contratar Seguro\n"+
            "2. Editar Seguro (ingresar ID)\n"+
            "3. Eliminar Seguro (ingresar ID)\n"+
            "4. Listar Seguros\n"
        )
    }

    fun submenuContratoAdmin(){
        mostrarMensaje("\n1. Hogar\n"+
                "2. Coche\n"+
                "3. Moto\n"
        )
    }

    fun menuConsulta(){
        mostrarMensaje("\n1. Seguros"+
            "2. Salir\n"
        )
    }

    fun submenuConsulta(){
        mostrarMensaje("1. Listar Seguros\n")
    }

    fun menu(){
        var assist = true
        try {
            do {
                modoEjecucion()
                mostrarMensaje("Seleccione una opcion: ")
                val opcionModo = readln()
                if (opcionModo.lowercase() == "simulacion"){}
                if (opcionModo.lowercase() == "almacenamiento"){}
            }while (assist)

        }catch (e:IllegalArgumentException){
            mostrarMensaje("**Error** Tipo de dato inválido!!!")
        }
    }
}