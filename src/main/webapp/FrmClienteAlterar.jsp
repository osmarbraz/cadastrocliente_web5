<!DOCTYPE html>
<html lang="pt-br" xml:lang="pt-br">
    <head>
        <title>Cadastro de Cliente - Alterar</title>
    </head>
    <body>
        <h1>Cadastro de Cliente - Alterar</h1>
        <form name="FrmCliente" method="post" action="servlet/ClienteAlterar">
            ClienteId: <input type=text id="ClienteId" name="ClienteId"> <br><br>
            Nome: <input type=text size="100" id="Nome" name="Nome"> <br><br>
            CPF (Somente números): <input type=text id="CPF" name="CPF"> <br><br>
            <input type="reset" id="Limpar" value="Limpar">
            <input type="submit" id="Alterar" name="Alterar" value="Alterar"> <br>
        </form>
        <br>
        <a href="<%=request.getContextPath()%>/menu.jsp"> Menu </a>
    </body>
</html>	