<?php
function consolelog($mensaje)
{
    if (is_array($mensaje))
        $res = "<script>console.log( 'JYOC_DEBUG -----------> : " . implode(',', $mensaje) . "' );</script>";
    else
        $res = "<script>console.log( 'JYOC_DEBUG -----------> : " . $mensaje . "' );</script>";
    echo $res;
}










?>