<?php
/* Smarty version 3.1.36, created on 2021-05-26 19:20:50
  from '/Users/inaki/Workspaces/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_smarty/cliente/templates/listaLibrosSimple.tpl' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.36',
  'unifunc' => 'content_60ae8372e27af1_93355889',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '8b17b77510243e9d0fe1736e878dd6978b2d524f' => 
    array (
      0 => '/Users/inaki/Workspaces/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_smarty/cliente/templates/listaLibrosSimple.tpl',
      1 => 1622049648,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
    'file:header.tpl' => 1,
    'file:footer.tpl' => 1,
  ),
),false)) {
function content_60ae8372e27af1_93355889 (Smarty_Internal_Template $_smarty_tpl) {
$_smarty_tpl->_checkPlugins(array(0=>array('file'=>'/Users/inaki/Workspaces/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_smarty/cliente/smarty/libs/plugins/modifier.date_format.php','function'=>'smarty_modifier_date_format',),1=>array('file'=>'/Users/inaki/Workspaces/JYOC_HTDOCS/00.JYOC_GUIAS_RAPIDAS/4_JG_PHP/JG_PHP_smarty/cliente/smarty/libs/plugins/modifier.capitalize.php','function'=>'smarty_modifier_capitalize',),));
$_smarty_tpl->_subTemplateRender("file:header.tpl", $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, 0, $_smarty_tpl->cache_lifetime, array('title'=>"Ejercicio JYOCSmarty"), 0, false);
?>
    Biblioteca de <?php echo $_smarty_tpl->tpl_vars['nombre']->value;?>

    <div class="contenedor">
        <h1>Relación de LIBROS</h1>
        <form action="" method="POST">
            <table>
                <tr>
                    <th>Codigo</th>
                    <th>Titulo</th>
                    <th>Autor</th>
                    <th>Editorial</th>
                    <th>Precio</th>
                    <th id="cabecera"></th>
                </tr>
                <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['listalibros']->value, 'cadalibro');
$_smarty_tpl->tpl_vars['cadalibro']->do_else = true;
if ($_from !== null) foreach ($_from as $_smarty_tpl->tpl_vars['cadalibro']->value) {
$_smarty_tpl->tpl_vars['cadalibro']->do_else = false;
?>
                <tr>
                    <td><?php echo $_smarty_tpl->tpl_vars['cadalibro']->value->getCodigo();?>
</td>
                    <td><?php echo $_smarty_tpl->tpl_vars['cadalibro']->value->getTitulo();?>
</td>
                    <td><?php echo $_smarty_tpl->tpl_vars['cadalibro']->value->getAutor();?>
</td>
                    <td><?php echo $_smarty_tpl->tpl_vars['cadalibro']->value->getEditorial();?>
</td>
                    <td><?php echo $_smarty_tpl->tpl_vars['cadalibro']->value->getPrecio();?>
</td>
                </tr>
                <?php
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl, 1);?>
            </table>
        </form>
    </div>

    ==============  otros ejemplos de uso de smarty
    <br>
    Fecha y hora actuales:  <?php echo smarty_modifier_date_format(time(),"%Y-%m-%d %H:%M:%S");?>
 
    <br>
    La variable de entorno SERVER_NAME es : <?php echo $_SERVER['SERVER_NAME'];?>

    <br>
    La variable de usuario $mensajecorto es : <?php echo $_smarty_tpl->tpl_vars['mensajecorto']->value;?>

    <br>
    La variable de usuario $mensajecorto toda en mayusculas es  <?php echo mb_strtoupper($_smarty_tpl->tpl_vars['mensajecorto']->value, 'UTF-8');?>

    <br>
    La variable de usuario $mensajecorto "capitalizada" es  <?php echo smarty_modifier_capitalize($_smarty_tpl->tpl_vars['mensajecorto']->value);?>

    <br>
    Mas ejemplos en la carpeta smarty/demo/indexphp  y   smarty/demo/templates


<?php $_smarty_tpl->_subTemplateRender("file:footer.tpl", $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, 0, $_smarty_tpl->cache_lifetime, array(), 0, false);
}
}
