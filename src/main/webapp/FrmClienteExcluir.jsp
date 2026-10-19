<!DOCTYPE html>
<html lang="pt-BR" xml:lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Cliente - Excluir</title>
    </head>
    <body>
        <h1>Cadastro de Cliente - Excluir</h1>
        <form name="FrmCliente" method="post" action="servlet/ClienteExcluir">
            <label for="ClienteId">ClienteId:</label> 
            <input type="text" id="ClienteId" name="ClienteId"> <br><br>
            <input type="reset" id="Limpar" value="Limpar">
            <input type="submit" id="Excluir" name="Excluir" value="Excluir"> <br>
        </form>
        <br>
        <a href="<%=request.getContextPath()%>/menu.jsp"> Menu </a>
    </body>
</html>	