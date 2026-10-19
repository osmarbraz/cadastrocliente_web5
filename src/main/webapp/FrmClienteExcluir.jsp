<!DOCTYPE html>
<html lang="pt-br" xml:lang="pt-br">
    <head>
        <title>Cadastro de Cliente - Excluir</title>
    </head>
    <body>
        <h1>Cadastro de Cliente - Excluir</h1>
        <form name="FrmCliente" method="post" action="servlet/ClienteExcluir">
            ClienteId : <input type=text id="ClienteId" name="ClienteId"> <br><br>
            <input type="reset" id="Limpar" value="Limpar">
            <input type="submit" id="Excluir" name="Excluir" value="Excluir"> <br>
        </form>
        <br>
        <a href="<%=request.getContextPath()%>/menu.jsp"> Menu </a>
    </body>
</html>	