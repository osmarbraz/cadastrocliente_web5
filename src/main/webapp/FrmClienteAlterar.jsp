<!DOCTYPE html>
<html lang="pt-BR" xml:lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Cliente - Alterar</title>
    </head>
    <body>
        <h1>Cadastro de Cliente - Alterar</h1>
        <form name="FrmCliente" method="post" action="servlet/ClienteAlterar">
            <label for="ClienteId">ClienteId:</label> 
            <input type=text id="ClienteId" name="ClienteId"> <br><br>
            <label for="Nome">Nome:</label> 
            <input type=text size="100" id="Nome" name="Nome"> <br><br>
            <label for="CPF">CPF (somente números):</label>
            <input type="text" id="CPF" name="CPF"> <br><br>
            <input type="reset" id="Limpar" value="Limpar">
            <input type="submit" id="Alterar" name="Alterar" value="Alterar"> <br>
        </form>
        <br>
        <a href="<%=request.getContextPath()%>/menu.jsp"> Menu </a>
    </body>
</html>	