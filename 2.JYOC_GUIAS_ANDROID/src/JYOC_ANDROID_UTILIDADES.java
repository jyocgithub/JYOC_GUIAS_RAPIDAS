package android;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.rmi.RemoteException;

import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import static android.content.Context.MODE_PRIVATE;


/*
 * #####################################################
 * ################  GUIA DE CONTENIDOS ################
 * #####################################################
 *
 * PERMISOS   (ANDROID)
 * ################################################################################
 *     - confirmarQueExistenTodosEstosPermisos       (Activity actividad, String... arrayPermisos)
 *     -  onRequestPermissionsResult                 (int requestCode, String permissions[], int[] grantResults)
 *
 * PREFERENCIAS   (ANDROID)
 * ################################################################################
 *     - leerPreferencias            (Context context)
 *     - guardarPreferencias         ()
 *
 * ALMACENAMIENTO EN DISPOSITIVO (ANDROID)
 * ################################################################################
 *     - salvarBitMapEnUnidadInternaApp      (Context context, Bitmap bitmap, String nombredelficheroDestino)
 *     - salvarStringEnUnidadInterna         (Context context, String contenido, String nombredelficheroDestino)
 *     - salvarBitmapEnUnidadExterna         (String DIRECTORIOEXTERNO, Bitmap bitmap, String carpetadestino, String nombreficherodestino)
 *     - salvarStringEnUnidadExterna         (String DIRECTORIOEXTERNO, String contenido, String carpetadestino, String nombreficherodestino)
 *     - obtenerFileDeFicheroEnUnidadExterna (String DIRECTORIOEXTERNO, String carpetaorigen, String nombrefichero)
 *     - obtenerUriDesdeUnFile               (File file)
 *     - obtenerBitmapDesdeUriDeUnidadInternaDeImagenes                  (Context ctx, Uri uri)
 *     - ponerImagenEnImageViewDesdeFicheroDeUnidadInternaDeImagenes     (Context context, ImageView view, String nombreficheroimagen)
 *     - ponerImagenConFormatoRedondoEnImageView     (Context context, Bitmap bitmap, ImageView imageview)
 *     - obtenerDirectorioEnUnidadExterna            (String DIRECTORIOEXTERNO, String nuevaCarpeta)
 *     - leerJSONDesdeUrl                            (String urlALeer)
 *     - copiarFileByteaByte                         (FileInputStream forigen, FileOutputStream fdestino)
 *     - crearTempFileDesdeString   VERSION JAVA     (String conten, String nombrefile, String extensionfile, String dirtem)
 *     - crearTempFileDesdeString   VERSION ANDROID  (Context context, String conten, String nombrefile, String extensionfile)
 *
 * COMUNICACION CON OTRAS APPS   (ANDROID)
 * ################################################################################
 *
 *     - hacerLlamadaTelefonica          (Activity actividad, String telefono)
 *     - enviarSMS                       (Activity actividad, String texto, String numerodestino)
 *     - enviarWhatsappTextoSinNumero    (Activity actividad, String mensaje)
 *     - enviarWhatsappANumero           (Activity actividad, String numero, String mensaje)
 *     - enviarMail                      (Activity actividad, String asunto, String mensaje, String destinatariosSeparadosPorComas)
 *     - enviarMailConFichero            (Activity actividad, String asunto, String mensaje, String destinatariosSeparadosPorComas)
 *     - tomarScreenShot                 (Activity actividad)
 *     - compartirTextoConOtrasAPPs      (String texto)
 *     - compartirArchivoConOOtrasApps   (File file, Activity actividad)
 *     - hacerFotoSoloParaThumbnail      (Activity actividad)
 *     - insertarContacto                (String pNombre, String pNumeroTelefono) {
 *
 * NOTIFICACIONES (ANDROID)
 * ################################################################################
 *
 *     - lanzarUnaNotification           (Context context, Bitmap icono, String... args)
 *
 *
 * ################ (fin)
*/


/*
    #####################################################
    ################  TABLA DE TIPOS MIME ###############
    #####################################################

    Esta lista muestra los tipos de ficheros mas habituales y su tipo MIME asociado

    * cualquier archivo    -> * /*
    * cualquier imagen     -> image/*
    * cualquier texto      -> text/*
    * cualquier audio      -> audio/*
    * cualquier video      -> video/*
    * .xml     -> text/xml
    * .txt     -> text/plain
    * .cfg     -> text/plain
    * .csv     -> text/plain
    * .conf    -> text/plain
    * .rc      -> text/plain
    * .htm     -> text/html
    * .html    -> text/html
    * .png     -> image/png
    * .gif     -> image/gif
    * .jpeg    -> image/jpeg
    * .jpg     -> image/jpeg
    * .mpeg    -> audio/mpeg
    * .aac     -> audio/aac
    * .wav     -> audio/wav
    * .ogg     -> audio/ogg
    * .midi    -> audio/midi
    * .wma     -> audio/x-ms-wma
    * .mpg     -> audio/mpeg4-generic
    * .mp4     -> video/mp4
    * .msv     -> video/x-msvideo
    * .wmv     -> video/x-ms-wmv
    * .pdf     -> application/pdf
    * .apk     -> application/vnd.android.package-archive

    ################ (fin)
 */


public class UtilsAndroidV6 {


    /**
     * ***************************************************************************
     * ************            PETICION DE PERMISOS    (ANDROID)    **************
     * ***************************************************************************
     */

    // **********************+++++++++++++++++++++++***************************************
    // **                     INICIO BLOQUE PETICION PERMISOS                            **
    // ********++++++++++++++++++++++++****************************************************
    //
    // Procedimiendo de uso (en la primera actividad de la aplicacion):
    // 1.- Renombrar el método onCreate(savedInstanceState);  como
    //        onCreate_conAccionesSoloConPermiso(savedInstanceState);
    // 2.- Quitar las lineas
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_main);
    // 3.- Copiar este bloque entero en esa misma primera actividad de la aplicacion y descomentarlo
    // 4.- Modificar el arrayDePermisosSolicitados para incluir solo los que se necesita pedir
    // 5.- Añadir esos mismos permisos en el Android.Manifest con lineas como estas:
    //        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    //private Bundle savedInstanceState;
    //private static final int PETICION_DE_PERMISOS_DESDE_ONCREATE = 12321;
    //private String[] arrayDePermisosSolicitados = {
    //        Manifest.permission.INTERNET,
    //        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    //        Manifest.permission.READ_CONTACTS,
    //        Manifest.permission.WRITE_CONTACTS,
    //        Manifest.permission.READ_EXTERNAL_STORAGE};
    //
    ///**
    // * onCreate
    // * Sustituye al onCreate original de la actividad
    // * Cuidado que si esta no es MainActivity hay que modificar el setContentView
    // *
    // * @param savedInstanceState  Bundle con el estado de la actividad
    // */
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    this.savedInstanceState = savedInstanceState;
    //    setContentView(R.layout.activity_main);
    //    confirmarQueExistenTodosEstosPermisos(this, savedInstanceState, arrayDePermisosSolicitados);
    //}//  FIN nuevo onCreate
    //
    ///**
    // * confirmarQueExistenTodosEstosPermisos
    // * <p>
    // * Solicita una serie de permisos si no se han concedido previamente por el usuario
    // *
    // * @param actividad            actividad donde se piden los permisos
    // * @param savedInstanceState   bundle con el estado de la actividad
    // * @param arrayPermisos        array con los permisos solicitados
    // */
    //public void confirmarQueExistenTodosEstosPermisos(Activity actividad, Bundle savedInstanceState, String... arrayPermisos) {
    //    boolean todosLosPemisosOk = true;
    //    for (String cadapermiso : arrayPermisos) {
    //        int permiso = ContextCompat.checkSelfPermission(actividad, cadapermiso);
    //        if (!(ContextCompat.checkSelfPermission(actividad, cadapermiso) == PackageManager.PERMISSION_GRANTED)) {
    //            todosLosPemisosOk = false;
    //        }
    //    }
    //    if (todosLosPemisosOk) {
    //        //-- RECORDAR HACER AQUI LO QUE SE DESEE CUANDO HAY PERMISOS   ---------------
    //        //-- LO NORMAL ES LLAMAR A UN METODO QUE COMPLETE EL ONCREATE  ---------------
    //        onCreate_conAccionesSoloConPermiso(savedInstanceState);
    //    } else {
    //        ActivityCompat.requestPermissions(actividad, arrayPermisos, PETICION_DE_PERMISOS_DESDE_ONCREATE);
    //    }
    //}//  FIN confirmarQueExistenTodosEstosPermisos
    //
    ///**
    // * onRequestPermissionsResult
    // * Complemento al metodo confirmarQueExistenTodosEstosPermisos, que solicita permisos
    // * <p>
    // * Recordar modificar si se desea las acciones que se deseen realizar si se conceden
    // * o si no se conceden los permisos
    // * La correcta concesion de permisos, actualmente,  llama al antiguo OnCreate que
    // * se renombro como     onCreate_conAccionesSoloConPermiso
    // *
    // * @param requestCode  codigo de quien solicito los permisos
    // * @param permissions  array de permisos que se han pedido al usuario
    // * @param grantResults array de respuestas del usuario a los permisos pedidos
    // */
    //public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    //    if (requestCode == PETICION_DE_PERMISOS_DESDE_ONCREATE) {
    //        if (grantResults.length > 0) {
    //            boolean todosLosPemisosOk = true;
    //            for (int i = 0; i < permissions.length; i++) {
    //                if (!(grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
    //                    todosLosPemisosOk = false;
    //                }
    //            }
    //            if (todosLosPemisosOk) {
    //                //-- RECORDAR HACER AQUI LO QUE SE DESEE CUANDO HAY PERMISOS   ---------------
    //                //-- LO NORMAL ES LLAMAR A UN METODO QUE COMPLETE EL ONCREATE  ---------------
    //                onCreate_conAccionesSoloConPermiso(savedInstanceState);
    //
    //            } else {
    //                //-- RECORDAR HACER AQUI LO QUE SE DESEE SI NO HAY PERMISOS ---------------
    //                finish(); // algun permiso no se otorgó, terminamos la actividad, no se deja seguir
    //            }
    //        } else {
    //            //-- RECORDAR HACER AQUI LO QUE SE DESEE SI NO HAY PERMISOS ---------------
    //            finish(); // se canceló al solicitar permisos, terminamos la actividad, no se deja seguir
    //        }
    //    }
    //}//  FIN onRequestPermissionsResult

    // **********************+++++++++++++++++++++++***************************************
    // **            FIN BLOQUE PETICION PERMISOS                                        **
    // ********++++++++++++++++++++++++****************************************************


    /**
     * ***************************************************************************
     * ************            PREFERENCIAS       (ANDROID)         **************
     * ***************************************************************************
     */
    //:::::::::::::::::::::::::::::::  Atributos
    public static SharedPreferences preferenciasPrivadas, preferenciasPublicas;
    //:::::::::::::::::::::::::::::::  Metodos

    /**
     * leerPreferencias
     * <p>
     * Patron para leer preferencias, tanto públicas como privadas
     * CUIDADO REQUIERE IMPORTAR LIBRERIAS EN EL GRADLE:   implementation "androidx.preference:preference:1.1.0"
     *
     * @param context
     */
    public static void leerPreferencias(Context context) {
        // Las publicas
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // cuidado REQUIERE IMPORTAR LIBRERIAS EN EL GRADLE:  implementation "androidx.preference:preference:1.1.0"
            preferenciasPublicas = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        } else {
            preferenciasPublicas = android.preference.PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        }
        // Las privadas
        preferenciasPrivadas = context.getSharedPreferences("MisPreferenciasPrivadas", MODE_PRIVATE);
        // int numero = preferenciasPrivadas.getInt("base", 10);
        // String nombre = preferenciasPrivadas.getString("nombre", "nadapordefecto");
    }

    /**
     * guardarPreferencias
     * <p>
     * Patron para guardar preferencias, tanto públicas como privadas
     */
    public static void guardarPreferencias() {
        // vale par PUBLICAS o PRIVADAS
        SharedPreferences.Editor editor = preferenciasPrivadas.edit();
        editor.putInt("tamanotitulo", 16);
        editor.putInt("tamanocontenido", 14);
        // editor.putString("nombre", "Juan");
        editor.commit();
    }



    /**
     * ***************************************************************************
     * ************     ALMACENAMIENTO EN DISPOSITIVO    (ANDROID)  **************
     * ***************************************************************************
     */

    /**
     * salvarBitMapEnDirectorioInternoApp
     * <p>
     * Guarda un Bitmap en un fichero, en el directorio de imagenes de la aplicacion (Internal Storage)
     * Devuelve un File que referencia directamente al fichero creado
     *
     * @param context
     * @param bitmap
     * @param nombredelficheroDestino
     *
     * @return Devuelve un File que referencia directamente al fichero creado
     */
    public static File salvarBitMapEnUnidadInternaApp(Context context, Bitmap bitmap, String nombredelficheroDestino) {
        ContextWrapper wrapper = new ContextWrapper(context);
        File carpeta = wrapper.getDir("Images", MODE_PRIVATE);
        File fileDestino = new File(carpeta, nombredelficheroDestino);
        try {
            OutputStream stream = new FileOutputStream(fileDestino);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDestino;
    }

    /**
     * salvarStringEnUnidadInterna
     * <p>
     * Guarda un Bitmap en un fichero, en el directorio de imagenes de la aplicacion (Internal Storage)
     * Devuelve un File que referencia directamente al fichero creado
     *
     * @param context
     * @param contenido
     * @param nombredelficheroDestino
     *
     * @return Devuelve un File que referencia directamente al fichero creado
     */
    public static File salvarStringEnUnidadInterna(Context context, String contenido, String nombredelficheroDestino) {
        ContextWrapper wrapper = new ContextWrapper(context);
        // AL indicar "Images" almacena en /data/data/(nuestraappid)/app_Images ... aunque se puede
        // poner cualquier cosa, "Almacen" por ejemplo, y lo crea en /data/data/(nuestraappid)/app_Almacen
        File carpeta = wrapper.getDir("Images", MODE_PRIVATE);
        File fileDestino = new File(carpeta, nombredelficheroDestino);
        try {
            if (!fileDestino.exists())
                fileDestino.createNewFile();  // por si acaso android en el futuro ..... hace algo raro
            PrintWriter pw = new PrintWriter(fileDestino);
            pw.println(contenido);
            pw.close();
            return fileDestino;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDestino;
    }

    /**
     * salvarBitmapEnEnDirectorioExterno
     * <p>
     * Si el directorio elegido no existe, intenta crearlo y devolverlo
     * guardarStringEnFicheroExterno
     *
     * @param DIRECTORIOEXTERNO    Ubicacion RAIZ del almacenamieno externo
     *                             El valor de DIRECTORIOEXTERNO que se puden usar con este metodo son (entre otros):
     *                             Environment.DIRECTORY_PICTURES
     *                             Environment.DIRECTORY_DOCUMENTS
     *                             Environment.DIRECTORY_DCIM
     *                             Environment.DIRECTORY_MOVIES
     *                             Environment.DIRECTORY_MUSIC
     *                             Environment.DIRECTORY_PODCASTS
     * @param carpetadestino
     * @param nombreficherodestino
     *
     * @return Devuelve un File que referencia directamente al fichero creado, o null si hay error
     */
    public static File salvarBitmapEnUnidadExterna(String DIRECTORIOEXTERNO, Bitmap bitmap, String carpetadestino, String nombreficherodestino) {
        boolean res = true;
        File fileDestino = null;
        try {
            File carpetaDestino = UtilsAndroidV5.obtenerDirectorioEnUnidadExterna(DIRECTORIOEXTERNO, carpetadestino);


            if (carpetaDestino != null) {
                fileDestino = new File(carpetaDestino, nombreficherodestino);
                if (!fileDestino.exists())
                    fileDestino.createNewFile();  // por si acaso android en el futuro ..... hace algo raro

                OutputStream stream = new FileOutputStream(fileDestino);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.flush();
                stream.close();
                return fileDestino;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * salvarStringEnEnDirectorioExterno
     * <p>
     * Si el directorio elegido no existe, intenta crearlo y devolverlo
     * guardarStringEnFicheroExterno
     *
     * @param DIRECTORIOEXTERNO    Ubicacion RAIZ del almacenamieno externo
     *                             El valor de DIRECTORIOEXTERNO que se puden usar con este metodo son (entre otros):
     *                             Environment.DIRECTORY_PICTURES
     *                             Environment.DIRECTORY_DOCUMENTS
     *                             Environment.DIRECTORY_DCIM
     *                             Environment.DIRECTORY_MOVIES
     *                             Environment.DIRECTORY_MUSIC
     *                             Environment.DIRECTORY_PODCASTS
     * @param carpetadestino
     * @param nombreficherodestino
     *
     * @return Devuelve un File que referencia directamente al fichero creado
     */
    public File salvarStringEnUnidadExterna(String DIRECTORIOEXTERNO, String contenido, String carpetadestino, String nombreficherodestino) {
        boolean res = true;
        File fileDestino = null;
        try {
            File carpetaDestino = UtilsAndroidV5.obtenerDirectorioEnUnidadExterna(DIRECTORIOEXTERNO, carpetadestino);
            if (carpetaDestino != null) {
                fileDestino = new File(carpetaDestino, nombreficherodestino);
                if (!fileDestino.exists())
                    fileDestino.createNewFile();  // por si acaso android en el futuro ..... hace algo raro
                PrintWriter pw = new PrintWriter(fileDestino);
                pw.println(contenido);
                pw.close();
                return fileDestino;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileDestino;
    }

    /**
     * obtenerFileDeFicheroEnDirectorioExterno
     * <p>
     * Obtiene un objeto file de un fichero en el almacenamiento externo, dado un directorio donde buscar y nombre del fichero
     *
     * @param DIRECTORIOEXTERNO Ubicacion RAIZ del almacenamieno externo
     *                          El valor de DIRECTORIOEXTERNO que se puden usar con este metodo son (entre otros):
     *                          Environment.DIRECTORY_PICTURES
     *                          Environment.DIRECTORY_DOCUMENTS
     *                          Environment.DIRECTORY_DCIM
     *                          Environment.DIRECTORY_MOVIES
     *                          Environment.DIRECTORY_MUSIC
     *                          Environment.DIRECTORY_PODCASTS     *
     * @param carpetaorigen
     * @param nombrefichero
     *
     * @return
     */
    public static File obtenerFileDeFicheroEnUnidadExterna(String DIRECTORIOEXTERNO, String carpetaorigen, String nombrefichero) {
        boolean res = true;
        File file = null;
        try {
            // se crea variable separada por conveniencia, para facilitar una depuracion
            String pathcompleto = Environment.getExternalStoragePublicDirectory(DIRECTORIOEXTERNO) + File.separator + carpetaorigen;
            file = new File(pathcompleto, nombrefichero);
            if (file.exists()) {
                return file;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * obtenerUriDesdeUnFile
     * <p>
     * Obtiene un objeto URI desde un objeto FILE
     *
     * @param file
     *
     * @return
     */
    public static Uri obtenerUriDesdeUnFile(File file) {
        Uri uriDelDFichero = Uri.fromFile(new File(file.getAbsolutePath()));
        return uriDelDFichero;
    }

    /**
     * obtenerUnBitmapDesdeUnaUriDelDirInternoDeImagenes
     * <p>
     * Obtiene un Bitmap desde una URI que apunta a una imagen del directorio de imagenes de la aplicacion (Internal Storage)
     *
     * @param ctx
     * @param uri
     *
     * @return
     */
    public static Bitmap obtenerBitmapDesdeUriDeUnidadInternaDeImagenes(Context ctx, Uri uri) {
        Bitmap bm = null;
        try {
            bm = MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * ponerImagenEnImageViewDesdeFicheroDeImagenesDeAplicacion
     * <p>
     * Pone en un ImageView el contenido de una imagen que esta en el directorio de imagenes de la aplicacion (Internal Storage)
     *
     * @param context
     * @param view
     * @param nombreficheroimagen
     *
     * @return
     */
    public static boolean ponerImagenEnImageViewDesdeFicheroDeUnidadInternaDeImagenes(Context context, ImageView view, String nombreficheroimagen) {
        File file = new File(new ContextWrapper(context).getDir("Images", MODE_PRIVATE), nombreficheroimagen);
        if (file.exists()) {
            Uri uri = UtilsAndroidV5.obtenerUriDesdeUnFile(file);
            Bitmap bitmapdesdedisco = UtilsAndroidV5.obtenerBitmapDesdeUriDeUnidadInternaDeImagenes(context, uri);
            view.setImageBitmap(bitmapdesdedisco);
            // -- Si se desea que quede imagen redonda usar esta linea en vez de la anterior:
            //ponerImagenConFormatoRedondoEnImageView(context, bitmapdesdedisco, view);
            return true;
        }
        return false;
    }


    /**
     * ponerImagenConFormatoRedondoEnImageView
     * <p>
     * Pone en un ImageView una imagen, pero el ImageVIew queda con formato circular
     *
     * @param context
     * @param bitmap
     * @param imageview
     */
    public static void ponerImagenConFormatoRedondoEnImageView(Context context, Bitmap bitmap, ImageView imageview) {
        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        circularBitmapDrawable.setCircular(true);
        imageview.setImageDrawable(circularBitmapDrawable);
        imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    /**
     * obtenerDirectorioEnCarpetaExterna
     * <p>
     * Si el directorio elegido no existe, intenta crearlo y devolverlo
     * Si no se puede crear devuelve null, si no, devuelve el directorio existente o creado
     *
     * @param DIRECTORIOEXTERNO Ubicacion RAIZ del almacenamieno externo
     *                          El valor de DIRECTORIOEXTERNO que se puden usar con este metodo son (entre otros):
     *                          Environment.DIRECTORY_PICTURES
     *                          Environment.DIRECTORY_DOCUMENTS
     *                          Environment.DIRECTORY_DCIM
     *                          Environment.DIRECTORY_MOVIES
     *                          Environment.DIRECTORY_MUSIC
     *                          Environment.DIRECTORY_PODCASTS
     * @param nuevaCarpeta
     *
     * @return Si no se puede crear devuelve null, si no, devuelve el directorio existente o creado
     */
    public static File obtenerDirectorioEnUnidadExterna(String DIRECTORIOEXTERNO, String nuevaCarpeta) {
        File file=new File(Environment.getExternalStoragePublicDirectory(DIRECTORIOEXTERNO), nuevaCarpeta);
        if (file.exists()) {
            return file;
        }
        if (!file.mkdirs()) {
            file = null;
        }
        return file;
    }


    /**
     * leerJSONDesdeUrl
     * <p>
     * Lee un JSON desde una direcion de Internet
     * Devuelve en un String el JSON leido
     *
     * @param urlALeer
     *
     * @return
     */
    public static String leerJSONDesdeUrl(String urlALeer) {
        URL url = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        BufferedReader br_delaurl = null;
        String todo = "";
        try {
            url = new URL(urlALeer);
            br_delaurl = new BufferedReader(new InputStreamReader(url.openStream()));
            String linea;
            while ((linea = br_delaurl.readLine()) != null) {
                todo += linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
            todo = null;
        } finally {
            if (br_delaurl != null) {
                try {
                    br_delaurl.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    todo = null;
                }
            }
            return todo;
        }
    }


    /**
     * Crea una copia de forigen en fdestino, copiando byte a byte
     * Si fdestino existe, lo sobreescribe completamente
     *
     * @param forigen
     *            - FileInputStream for the file to copy from.
     * @param fdestino
     *            - FileInputStream for the file to copy to.
     */
    public static void copiarFileByteaByte(FileInputStream forigen, FileOutputStream fdestino) throws IOException {
        FileChannel channelOrigen = null;
        FileChannel channelDestino = null;
        try {
            channelOrigen = forigen.getChannel();
            channelDestino = fdestino.getChannel();
            channelOrigen.transferTo(0, channelOrigen.size(), channelDestino);
        } finally {
            try {
                if (channelOrigen != null) {
                    channelOrigen.close();
                }
            } finally {
                if (channelDestino != null) {
                    channelDestino.close();
                }
            }
        }
    }




 /**
     * crearTempFileDesdeString   VERSION JAVA; NO ANDROID
     *
     * Crea un fichero temporal con el contenido de un string
     * El fichero se borra automaticamente al abandonar la app
     *
     * @param conten      contenido del fichero
     * @param nombrefile  contenido del fichero
     * @param extensionfile  extension del fichero
     * @param dirtem  dir donde guardar el fichero temporal
     * @return objeto file con la referencia del fichero temporeal creado
     */
     public static File crearTempFileDesdeString(String conten, String nombrefile, String extensionfile, String dirtem) {
        File temp=null;
        try {
            // El fichero se crea, pero solo el objeto FiLe, su referencia.
             temp = File.createTempFile(nombrefile, extensionfile, new File(dirtem));

            // El fichero se borrará, el solo, cuando el programa termine
            temp.deleteOnExit();

            // Escribir el contenido del string en el fichero temp
            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write(conten);
            out.close();
        } catch (IOException e) {
        } finally {
            return temp;
        }
    }

     /**
     * crearTempFileDesdeString   VERSION ANDROID: NO JAVA
     *
     * Crea un fichero temporal con el contenido de un string
     * El fichero se borra automaticamente al abandonar la app
     *
     * @param context     contexto de creacion
     * @param conten      contenido del fichero
     * @param nombrefile  contenido del fichero
     * @param extensionfile  extension del fichero
     * @return objeto file con la referencia del fichero temporeal creado
     */
    public static File crearTempFileDesdeString(Context context, String conten, String nombrefile, String extensionfile) {
        File temp=null;
        try {
            // El directorio temporal se recoge con la funcion getCacheDir()
            File dirtem = context.getCacheDir();

            // El fichero se crea, pero solo el objeto FiLe, su referencia.
             temp = File.createTempFile(nombrefile, extensionfile, dirtem);

            // El fichero se borrará, el solo, cuando el programa termine
            //temp.deleteOnExit();

            // Escribir el contenido del string en el fichero temp
            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write(conten);
            out.close();
        } catch (IOException e) {
        } finally {
            return temp;
        }
    }




    /**
     * ***************************************************************************
     * ************            ENVIANDO A OTRAS APPS   (ANDROID)    **************
     * ***************************************************************************
     */


    /**
     * hacerLlamadaTelefonica
     *
     * Hace una llamada al numero que se le pasa como parametro
     * REQUIERE PERMISO OTORGADO PARA Manifest.permission.CALL_PHONE
     * @param actividad
     * @param telefono
     */
    //    public static final int JYOC_CONSTANTE_HACER_LLAMADA_TELEFONICA = 1512;
    public static void hacerLlamadaTelefonica(Activity actividad, String telefono) {

        if (actividad.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:" + telefono));
            // LA LINEA SIGUIENTE  SE DESCOMENTA AL APLICAR LOS PERMISOS NECESARIOS
            //actividad.startActivity(i);
        } else {
            Toast.makeText(actividad.getApplicationContext(), "NECESITA OTORGAR PERMISOS PARA LLAMADAS ", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * enviarSMS
     * <p>
     * Envia un SMS aun numero determinado
     *
     * @param actividad
     * @param texto
     * @param numerodestino
     */
    public static void enviarSMS(Activity actividad, String texto, String numerodestino) {
        Uri uri = Uri.parse("smsto:" + numerodestino);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra("sms_body", texto);
        //i.setPackage("com.whatsapp");
        actividad.startActivity(i);
    }

    /**
     * enviarWhatsappTextoSinNumero
     * <p>
     * Abre whatsapp con un texto preparado y espera a que el usuario elija destinatario
     * <p>
     * Si se desea una accion personalizada si no existe Whatsapp instalado en el dispositivo,
     * añadir las lineas comentadas y cambiar la accion en el catch
     *
     * @param actividad
     * @param mensaje
     */
    public static void enviarWhatsappTextoSinNumero(Activity actividad, String mensaje) {
        //--- Si se desea una accion personalizada si no existe Whatsapp instalado en el dispositivo,
        //--- añadir las lineas comentadas y cambiar la accion en el catch
        // PackageManager pm = actividad.getPackageManager();
        //  try {
        //PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp"); // Si no se especifica la app aqui, Android muetra el "application picker" o selector de aplicaciones.
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);
        actividad.startActivity(Intent.createChooser(intent, "Compartiendo con whatsapp..."));
        //} catch (PackageManager.NameNotFoundException e) {
        //    Toast.makeText(actividad, "WhatsApp no esta instalado", Toast.LENGTH_SHORT).show();
        //}
    }


    /**
     * enviarWhatsappANumero
     * <p>
     * Abre whatsapp con un texto preparado y en el numero de destinatario indicado
     * Hay que cambiar el prefijo 34 si se va a usar con numero de fuera de España
     * Se puede no indicar el mensaje, con lo que abre solo el chat whatsapp del numero indicado
     *
     * <p>
     * Si se desea una accion personalizada si no existe Whatsapp instalado en el dispositivo,
     * añadir las lineas comentadas y cambiar la accion en el catch
     *
     * @param actividad
     * @param mensaje
     */
    public static void enviarWhatsappANumero(Activity actividad, String numero, String mensaje) {
        try {
            String numeroConPrefijo = "34" + numero;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + numeroConPrefijo + "&text=" + mensaje));
            actividad.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * enviarMail
     * <p>
     * Version que no pregunta al usuario por la app que desea usar sino que usa la app por defecto del dispositivo
     *
     * @param actividad
     * @param asunto
     * @param mensaje
     * @param destinatariosSeparadosPorComas
     */
    public static void enviarMail(Activity actividad, String asunto, String mensaje, String destinatariosSeparadosPorComas) {
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + destinatariosSeparadosPorComas));
        // -- EXTRA_SUBJECT Es el asunto del correo y EXTRA_TEXT el cuerpo del correo
        i.putExtra(Intent.EXTRA_SUBJECT, asunto);
        i.putExtra(Intent.EXTRA_TEXT, mensaje);
        // -- EXTRA_HTML_TEXT se usa si el texto del correo tiene formato HTML
        // i.putExtra(Intent.EXTRA_HTML_TEXT, "<bold>TEXTO DEL MENSAJE</bold>");
        // -- En este caso el segundo parametro del createChooser es un mensaje que se muestra
        // -- por pantalla cuando se efectua el envio
        actividad.startActivity(Intent.createChooser(i, "Enviando mensaje"));
    }

    /**
     * enviarMailConFichero
     * <p>
     * Version que adjunta un fichero al mail
     * USA EL CONCEPTO DE FILE PROVIDER; ASI QUE NECESITA AÑADIR ESTO:
     * ------------------------
     * - una etiqueta <provider> en el Manifest
     * <provider
     *        android:name=".GenericFileProvider"
     *        android:authorities="${applicationId}.fileprovider"
     *        android:exported="false"
     *        android:grantUriPermissions="true">
     *        <meta-data
     *            android:name="android.support.FILE_PROVIDER_PATHS"
     *            android:resource="@xml/provider_paths"/>
     * </provider>
     * ------------------------
     * - un xml que defina un recurso path llamada provider_paths (que es como se llamo en el provider anterior)
     * <?xml version="1.0" encoding="utf-8"?>
     * <paths xmlns:android="http://schemas.android.com/apk/res/android">
     *     <external-path
     *         name="external_files"
     *         path="." />
     * </paths>
     * ------------------------
     * <p>
     * En un Intent.setType(String mimeType) el String representa el tipo de dato MIME
     * que se desea obtener como respuesta al lanzar un intent
     * Esta lista muestra los tipos de ficheros mas habituales y su tipo MIME asociado
     * <p>
     * cualquier archivo    -> * /*
     * cualquier imagen     -> image/*
     * cualquier texto      -> text/*
     * cualquier audio      -> audio/*
     * cualquier video      -> video/*
     * .xml     -> text/xml
     * .txt     -> text/plain
     * .cfg     -> text/plain
     * .csv     -> text/plain
     * .conf    -> text/plain
     * .rc      -> text/plain
     * .htm     -> text/html
     * .html    -> text/html
     * .png     -> image/png
     * .gif     -> image/gif
     * .jpeg    -> image/jpeg
     * .jpg     -> image/jpeg
     * .mpeg    -> audio/mpeg
     * .aac     -> audio/aac
     * .wav     -> audio/wav
     * .ogg     -> audio/ogg
     * .midi    -> audio/midi
     * .wma     -> audio/x-ms-wma
     * .mpg     -> audio/mpeg4-generic
     * .mp4     -> video/mp4
     * .msv     -> video/x-msvideo
     * .wmv     -> video/x-ms-wmv
     * .pdf     -> application/pdf
     * .apk     -> application/vnd.android.package-archive
     *
     * @param actividad
     * @param asunto
     * @param mensaje
     * @param destinatariosSeparadosPorComas
     */
    public static void enviarMailConFichero(Activity actividad, String asunto, String mensaje, String[] arrayDestinatarios) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"javayotrascosas@gmail.com"});
        // EJEMPLOS-
        //intent.putExtra(Intent.EXTRA_SUBJECT, "subject here");
        //intent.putExtra(Intent.EXTRA_TEXT, "body text");
        //intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"javayotrascosas@gmail.com"});

        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT,  mensaje);
        intent.putExtra(Intent.EXTRA_EMAIL, arrayDestinatarios);

        File file = obtenerFileDeFicheroEnUnidadExterna(Environment.DIRECTORY_DOCUMENTS, "miscosas", "prueba.txt");
        // -- Otro ejemplo, se lee directamente del directorio PICTURES y se envia una foto
        // File file = obtenerFileDeFicheroEnDirectorioExterno(Environment.DIRECTORY_PICTURES, "", "pperilla_logo_rojo.png");

        Context con = actividad.getApplicationContext();
        String str = actividad.getApplicationContext().getPackageName() + ".fileprovider";
        Uri uri = FileProvider.getUriForFile(con, str, file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        actividad.startActivityForResult(Intent.createChooser(intent, "Enviando con adjunto..."), 3);
    }


    /**
     * tomarScreenShot
     *
     * Toma un ScreenShot de la pantalla actual y lo devuelve como un Bitmap
     * Para guadarlo en un fichero de disco, usar salvarBitmapEnEnDirectorioExternoApp() Ejemplo:
     *   Bitmap bitmap = JYOCUtilsv4.tomarScreenShot(mainactivity);
     *   File file = JYOCUtilsv4.salvarBitmapEnEnDirectorioExternoApp(Environment.DIRECTORY_PICTURES,bitmap,"ScreenShots","pantallazo.png");
     *
     * @param actividad
     */
    public static Bitmap tomarScreenShot(Activity actividad) {
        View rootView = actividad.getWindow().getDecorView().findViewById(android.R.id.content);
        View screenView = rootView.getRootView();
        Bitmap bitmap = Bitmap.createBitmap(screenView.getWidth(), screenView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = screenView.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        screenView.draw(canvas);
        return bitmap;
    }



    /**
     * compartirTextoConOtrasAPPs
     *
     *
     * Abre una ventana para que el usuario comparta un texto con las app del dispositivo
     * Recibe un String del texto que va a compartir
     *
     * @param texto    texto que se comparte
     *
     */
    public void compartirTextoConOtrasAPPs(String texto, Activity actividad){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        actividad.startActivity(shareIntent);
    }



    /**
     * Compartir
     *
     *  CUIDADO -- USA EL CONCEPTO DE FILE PROVIDER; ASI QUE NECESITA AÑADIR LO MISMO QUE EN EL METODO enviarMailConFichero
     *
     * Abre una ventana para que el usuario comparta el fichero con las app del dispositivo
     * Recibe un objeto File del fichero que va a compartir
     *
     * @param file
     * @param actividad
     */
    public static void compartirArchivoConOOtrasApps(File file, Activity actividad) {
        Context con = actividad.getApplicationContext();
        String str = actividad.getApplicationContext().getPackageName() + ".fileprovider";
        Uri uri = FileProvider.getUriForFile(con, str, file);
        //Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("*/*");

        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            actividad.startActivity(Intent.createChooser(intent, "Compartir...."));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(actividad.getApplicationContext(), "No hay aplicacion instalada para esta accion", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * hacerFotoSoloParaThumbnail
     *
     * Toma una foto de tyamaño thumbnail y en un posterior metodo onActivityResult se recoge el Bitmapo resultante
     * Exige el uso del metodo siguiente que se muestra comentado,  onActivityResult,
     *
     * @param file   Actividad a la que corresponde
     */
    public static final int JYOC_CONSTANTE_TOMAR_FOTO_PARA_THUMBNAIL = 17742;

    public static void hacerFotoSoloParaThumbnail(Activity actividad) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // el IF siguiente comprueba que haya una aplicacion en el movil que pueda hacer fotos
        if (takePictureIntent.resolveActivity(actividad.getPackageManager()) != null) {
            actividad.startActivityForResult(takePictureIntent, JYOC_CONSTANTE_TOMAR_FOTO_PARA_THUMBNAIL);
        }
    }
    /**
     * onActivityResult
     * Complemento al metodo anterior (hacerFotoSoloParaThumbnail) que evalua el contenido de la foto tomada
     * ESTE METODO HA DE DESCOMENTARSE Y COPIARSE EN LA ACTIVIDAD INICIAL, DONDE SE INCLUYE EL METODO ANTERIOR;
     * NO VALE INVOCARLO CON UN OBJETO DE ESTA CLASE
     * <p>
     * @param requestCode
     * @param resultCode
     * @param data
     */
    // DESCOMENTAR Y COPIAR EN LA ACTIVIDAD INICIAL DE LA APLICACION
    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    if (requestCode == JYOCUtilsv4.JYOC_CONSTANTE_TOMAR_FOTO_PARA_THUMBNAIL && resultCode == RESULT_OK) {
    //        Bundle extras = data.getExtras();
    //        Bitmap fotoTomada = (Bitmap) extras.get("data");
    //        File f = JYOCUtilsv4.salvarBitMapEnUnidadInternaApp(this, fotoTomada, "foto" + idDeFotoActual + ".jpg");
    //        Uri uri = JYOCUtilsv4.obtenerUriDesdeUnFile(f);
    //        // esto es redundante, ya que la imagen la tenemos en fotoTomada, y no hace falta volver a leerla de disco
    //        // queda asi como ejemplo de como coger un bitmap de un fichero de disco
    //        Bitmap bitmapdesdedisco = JYOCUtilsv4.obtenerBitmapDesdeUriDeUnidadInternaDeImagenes(this, uri);
    //    }
    //}








     /**
     * insertarContacto
     * Añade un nuevo contacto a la agenda del dispositivo usando Contents Provider
     * Solo añade un nombre y un telefono
     * <p>
     * @param pNombre nombre de la persona a añadir
     * @param pNumeroTelefono numero de movil de la persona a añadir
     */
     public void insertarContacto(String pNombre, String pNumeroTelefono) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactInsertIndex = ops.size();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, pNombre) // nombre del contacto
                .build());
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(
                        ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                .withValue(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, pNumeroTelefono) // numero de telefono
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build()); // Tipo de numero de telefono
        try {
            ContentProviderResult[] res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException | OperationApplicationException e) {
            Log.e("****JYOC", "Error añadiendo contacto");
        }
    }





    /**
     * ***************************************************************************
     * ************            NOTIFICACIONES   (ANDROID)           **************
     * ***************************************************************************
     */


   /**
     *
     * @param context PRIMER PARAMETRO, el contexto donde se lanza la notificacoin
     * @param args    ORDEN DEL RESTO DE PARAMETROS (TODOS STRING) NO OBLIGATORIO NINGUNO
     *                - texto del la notificacion
     *                - titulo de la notificacion
     *                - texto largo de la notificacion
     *                - channel_id
     *                - channel_name
     *                - notification_id
     */
    private void lanzarUnaNotification(Context context, Bitmap icono, String... args) {
        // cogemos de los parametros los valores pasados o cogemos unos por defecto
        final String TEXTO_NOTIFICACION = (args.length > 0) ? args[0] : "Aviso de " + context.getString(R.string.app_name);
        final String TITULO_NOTIFICACION = (args.length > 1) ? args[1] : "Notificación";
        final String TEXTO_LARGO=  (args.length > 2) ? args[2] : "";
        final String CHANNEL_ID = (args.length > 3) ? args[3] : context.getString(R.string.app_name);
        final String CHANNEL_NAME = (args.length > 4) ? args[4] : context.getString(R.string.app_name);
        final int NOTIFICATION_ID = (args.length > 5) ? Integer.parseInt(args[5]) : 1313;

        //Bitmap miBitMap  = BitmapFactory.decodeResource(getResources(),R.drawable.casa3);

        // 1.- Crear un Notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(context.getString(R.string.app_name));
            notificationChannel.setSound(null, null);
            notificationChannel.setLightColor(Color.GREEN);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // 2.- Crear el contenido de la notificacion
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icono)
                .setContentTitle(TITULO_NOTIFICACION)
                .setContentText(TEXTO_NOTIFICACION)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(TEXTO_LARGO))
                // En vez de un texto largo, se puede añadir como expansible una imagen grande. Usar en este caso este otro style:
                //.setStyle(new NotificationCompat.BigPictureStyle()
                //        .bigPicture(icono)
                //        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT); // ver tabla en pagina previa

        // 3.- Lanzar notificacion
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    public static final int JYOC_CONSTANTE_SELECCIONAR_FICHERO = 6645;

    /**
     * seleccionarFicheroDelDispositivo
     *
     * Permite al usuario elegir un fichero existenet en el dispositivo,
     * permitiendo filtrar por tipo de fichero
     * Exige el uso del metodo siguiente que se muestra comentado, onActivityResult
     * @param actividad
     * @param tipoMIME
     */
    public static void seleccionarFicheroDelDispositivo(Activity actividad, String tipoMIME) {
        //--  Version 1
        //--      - sin elegir explorador de archivos, se usara la aplicaion por defecto del movil
        //--      - para un tipo especifico de fichero
        //--      (cambiar el tipo MIME de seleccion de text a lo que se desee, ver patrones MIME en el metodo enviarMailConFichero() )
        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType(tipoMIME);  // cambiar el tipo MIME para seleccionar otros tipos de ficheros
        //actividad.startActivityForResult(intent, JYOC_CONSTANTE_SELECCIONAR_FICHERO);

        //-- Version 2
        //--      - para elegir entre las diferentes apps que puedan abrir el MIME seleccionado
        Intent intent_del_get = new Intent(Intent.ACTION_GET_CONTENT);
        intent_del_get.setType(tipoMIME);// cambiar el tipo MIME para seleccionar otros tipos de ficheros

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType(tipoMIME);// cambiar el tipo MIME para seleccionar otros tipos de ficheros

        Intent chooserIntent = Intent.createChooser(intent_del_get, "Seleccione archivo:");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        actividad.startActivityForResult(chooserIntent, JYOC_CONSTANTE_SELECCIONAR_FICHERO);

        //-- SI SE DESEA ELEGIR SOLO ENTRE CIERTOS TIPOS MIME, USAR ESTE MODELO :
        //final String[] SOLO_CIERTOS_MIME = {
        //        "application/pdf",
        //        "image/*"
        //};
        //intent.putExtra(Intent.EXTRA_MIME_TYPES, SOLO_CIERTOS_MIME);
    }
    /**
     * onActivityResult
     * Complemento al metodo anterior (seleccionarFicheroDelDispositivo) que trata el fichero seleccionado
     * ESTE METODO HA DE DESCOMENTARSE Y COPIARSE EN LA ACTIVIDAD INICIAL, DONDE SE INCLUYE EL METODO ANTERIOR;
     * NO VALE INVOCARLO CON UN OBJETO DE ESTA CLASE
     * <p>
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    // DESCOMENTAR Y COPIAR EN LA ACTIVIDAD INICIAL DE LA APLICACION
    //@Override
    //public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    if (requestCode == JYOCUtilsv4.JYOC_CONSTANTE_SELECCIONAR_FICHERO) {
    //        BufferedReader br = null;
    //        String fichCompleto = "", lin;
    //        FileInputStream inputStream = null;
    //        try {
    //            if (data != null) {  // data puede ser null si el usuario cancela la seleccion del archivo
    //                inputStream = (FileInputStream) this.getContentResolver().openInputStream(data.getData());
    //                //--  OPCION 1:
    //                //--  Si el fichero es un fichero de texto, asi lo leo y lo recojo en una variable String
    //                br = new BufferedReader(new InputStreamReader(inputStream));
    //                while ((lin = br.readLine()) != null) {
    //                    fichCompleto += lin;
    //                }
    //
    //                //--  OPCION 2:
    //                //--  Si el fichero es una imagen, asi obtengo un bitmap de la misma y lo pongo en un ImageView;
    //                //Bitmap fotoTomada  = BitmapFactory.decodeStream(inputStream);
    //                //im.setImageBitmap(fotoTomada);
    //                //im.setImageBitmap(fotoTomada);
    //                //im.setImageBitmap(fotoTomada);
    //                //im.setImageBitmap(fotoTomada);
    //                //im.setImageBitmap(fotoTomada);
    //            }
    //
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        } finally {
    //            try {
    //                if (br != null)
    //                    br.close();
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }
    //}










}