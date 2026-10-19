<!DOCTYPE html>
<html lang="pt-BR" xml:lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Cliente - Incluir</title>
    </head>
    <body>
        <h1>Cadastro de Cliente - Incluir</h1>
        <form name="FrmCliente" method="post" action="servlet/ClienteIncluir">	
            <label for="ClienteId">ClienteId:</label>
            <input type="text" id="ClienteId" name="ClienteId"> <br><br>
            <label for="Nome">Nome:</label> 
            <input type="text" size="100" id="Nome" name="Nome"> <br><br>
            <label for="CPF">CPF (somente números):</label>
            <input type=text id="CPF" name="CPF"> <br><br>
            <button type="reset" id="Limpar">Limpar</button>
            <button type="submit" id="Incluir" name="Incluir">Incluir</button>         
        </form>
        <br>
        <a href="<%=request.getContextPath()%>/menu.jsp"> Menu </a>
    </body>
</html>	