package br.ufscar.dc.compiladores.alguma.grammar;


import br.ufscar.dc.compiladores.alguma.grammar.AlgumaGrammarParser.DeclaracoesContext;
import br.ufscar.dc.compiladores.alguma.grammar.AlgumaGrammarParser.IdentificadorContext;
import br.ufscar.dc.compiladores.alguma.grammar.AlgumaGrammarParser.ParcelaContext;
import br.ufscar.dc.compiladores.alguma.grammar.AlgumaGrammarParser.Parcela_nao_unarioContext;
import br.ufscar.dc.compiladores.alguma.grammar.AlgumaGrammarParser.Parcela_unarioContext;
import br.ufscar.dc.compiladores.alguma.grammar.Escopos;
import br.ufscar.dc.compiladores.alguma.grammar.AlgumaSemanticoUtils;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.AlgumaGrammar;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.EntradaTabelaDeSimbolos;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.TipoEntrada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;

public class AlgumaSemantico extends AlgumaGrammarBaseVisitor<Void> {
    TabelaDeSimbolos tabela;
    TabelaDeSimbolos tabelaEscopos;

    static Escopos escoposAninhados = new Escopos();

    // Criação de uma tabela que armazenará as variáveis referentes a um registro
    static HashMap<String, ArrayList<String>> tabelaRegistro = new HashMap<>();
    static HashMap<String, ArrayList<AlgumaGrammar>> tabelaFuncaoProcedimento = new HashMap<>();

    @Override
    public Void visitPrograma(AlgumaGrammarParser.ProgramaContext ctx) {
        // Inicialização do programa.
        tabela = new TabelaDeSimbolos();
        return super.visitPrograma(ctx);
    }

    // Função auxiliar para definir o tipo na gramática
    public static AlgumaGrammar determinarTipoAlgumaGrammar(String tipoVariavel){
        AlgumaGrammar tipoItem = AlgumaGrammar.INVALIDO; 
        

        
        switch (tipoVariavel) {
            case "literal":
                tipoItem = AlgumaGrammar.LITERAL;
                break;
            case "inteiro":
                tipoItem = AlgumaGrammar.INTEIRO;
                break;
            case "real":
                tipoItem = AlgumaGrammar.REAL;
                break;
            case "logico":
                tipoItem = AlgumaGrammar.LOGICO;
                break;
            case "registro":
                tipoItem = AlgumaGrammar.REGISTRO;
                break;
            case "void":
                tipoItem = AlgumaGrammar.VOID;
                break;
            default: 
                if (tabelaRegistro.containsKey(tipoVariavel)) {
                    tipoItem = AlgumaGrammar.REGISTRO;
                }
                break;
        }
        return tipoItem;
    }

    // Adiciona variável e seu tipo na tabela de símbolos
    public void adicionaVariavelTabela(String nomeVariavel, String tipoVariavel, Token nomeToken, Token TipoToken, TipoEntrada tipoEnt, int tamArray) {
        tabelaEscopos = escoposAninhados.obterEscopoAtual();

        AlgumaGrammar tipoItem;
        boolean flagPonteiro = false;

        // Checa se o tipo da variável inicia com ^, identificando um ponteiro
        if (tipoVariavel.startsWith("^")) {
            // Caso sim, define a flag de ponteiro como verdadeira e remove o caractere ^ do tipo
            flagPonteiro = true;
            tipoVariavel = tipoVariavel.substring(1); 
        }

        // Definição do tipo na gramática
        tipoItem = determinarTipoAlgumaGrammar(tipoVariavel);


        // Verificando se o tipo da variável é 'INVÁLIDO' para retornar mensagem de erro
        if (tipoItem == AlgumaGrammar.INVALIDO) {
            tabelaEscopos.adicionar(nomeVariavel, tipoItem, tipoEnt, flagPonteiro,0);
            AlgumaSemanticoUtils.adicionarErroSemantico(TipoToken, 
                "tipo " + tipoVariavel + " nao declarado");
        }

        /*
         * Se o tipo da variável não for 'INVÁLIDO', verifica se ela já existe
         * Caso não exista: adiciona
         * Caso exista: retorna erro semântico, pois já foi declarada
         */
        else if (!tabelaEscopos.existe(nomeVariavel))

            tabelaEscopos.adicionar(nomeVariavel, tipoItem, tipoEnt, flagPonteiro, tamArray);
        
            else
            AlgumaSemanticoUtils.adicionarErroSemantico(nomeToken, 
                "identificador " + nomeVariavel + " ja declarado anteriormente");

    }

    // Visita as declarações
    @Override
    public Void visitDeclaracoes(AlgumaGrammarParser.DeclaracoesContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        for (AlgumaGrammarParser.Decl_local_globalContext declaracao : ctx.decl_local_global())
            visitDecl_local_global(declaracao);

        return super.visitDeclaracoes(ctx);
    }

    // Visita as declarações locais
    @Override
    public Void visitDeclaracao_local(AlgumaGrammarParser.Declaracao_localContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        String tipoVariavel;
        String nomeVariavel;
        // Caso 1: Declaração de variável
        if (ctx.getText().startsWith("declare")) {
            
            //System.out.println(ctx.variavel().identificador());

            // Caso 1.1: A declaração possui definição de registro
            // Etapa: Para cada atributo do registro, adiciona 'variavel.atributo' com tipo do atributo
            // na tabela de símbolo
            if (ctx.variavel().tipo().registro() != null) {
                //("Entrou no Caso 1.1!");
                for (var variavel : ctx.variavel().identificador()){
                    //System.out.println(variavel.getText());
                    System.out.println("\tVARIAVEL_REGISTRO: '"+variavel.getText() +"' com tipo "+ctx.variavel().tipo().getStart().getText());
                    // Adiciona a variável como registro
                    adicionaVariavelTabela(variavel.IDENT(0).getText(),
                        ctx.variavel().tipo().getStart().getText(),
                            variavel.getStart(),
                                ctx.variavel().tipo().getStart(),
                                    TipoEntrada.VARIAVEL,
                                        -1);

                    System.out.println(variavel.IDENT(0).getText() + " " + 
                        ctx.variavel().tipo().getStart().getText() + " " + 
                        variavel.getStart() + " " + 
                        ctx.variavel().tipo().getStart() );
                            
                    for(var variavel_registro: ctx.variavel().tipo().registro().variavel()){ 
                        //adicionaVariavelTabela(variavel.IDENT(0).getText(), variavel_registro.tipo().getText(), ident.getStart(), variavel_registro.tipo().getStart(), TipoEntrada.VARIAVEL, -1);    
                        // Adiciona na tabela de símbolos para cada um dos atributos do registro: variavel.atributo 
                        for (var ident: variavel_registro.identificador()){
                            nomeVariavel = variavel.IDENT(0).getText() + "." + ident.getText();
                            
                            adicionaVariavelTabela(nomeVariavel, 
                                variavel_registro.tipo().getText(), 
                                    ident.getStart(), 
                                        variavel_registro.tipo().getStart(), 
                                            TipoEntrada.VARIAVEL, -1);
                        
                            System.out.println("\t\tVisit Declaracao LOCAL: Variavel '"+variavel.IDENT(0).getText() + "." + ident.getText()+"'' adicionada à tabela com o tipo "+variavel_registro.tipo().getText());
                            System.out.println("\t\t\tIdent variavel registro '"+ident.getText() +"' com tipo "+variavel_registro.tipo().getText());
                            System.out.println("\t\t\t\tExiste a variavel '"+variavel.IDENT(0).getText()+"'?"+tabela.existe(variavel.IDENT().get(0).getText())+ " e o tipo eh: "+tabela.verificar(variavel.IDENT(0).getText()));    
                            System.out.println("\t\t\t\tExiste o atributo da variavel '"+nomeVariavel+"'?"+tabela.existe(nomeVariavel) + " e o tipo eh: "+tabela.verificar(nomeVariavel));    
                
                        }
                        
                    }
                    
                }
            // Caso 1.2: A declaração não possui definição de novo registro        
            } else { 
                System.out.println("\tEntrou no caso 1.2!");
                tipoVariavel = ctx.variavel().tipo().getText();
                //System.out.println(tipoVariavel);
                // Caso 1.2.1: verifica se o tipo é um registro já declarado
                if (tabelaRegistro.containsKey(tipoVariavel)) {
                    System.out.println("\t\tEntrou no caso 1.2.1!");
                    ArrayList<String> variavelRegistro = tabelaRegistro.get(tipoVariavel);

                    for (AlgumaGrammarParser.IdentificadorContext ic: ctx.variavel().identificador()) {
                        nomeVariavel = ic.IDENT().get(0).getText();

                        if (tabela.existe(nomeVariavel) || tabelaRegistro.containsKey(nomeVariavel)) 
                            AlgumaSemanticoUtils.adicionarErroSemantico(ic.getStart(), 
                                "identificador " + nomeVariavel + " ja declarado anteriormente");
                        else {
                            adicionaVariavelTabela(nomeVariavel, "registro", ic.getStart(), ctx.variavel().tipo().getStart(), TipoEntrada.VARIAVEL, -1);

                            for (int i = 0; i < variavelRegistro.size(); i = i + 2){
                                adicionaVariavelTabela(nomeVariavel + "." + variavelRegistro.get(i), variavelRegistro.get(i+1), ic.getStart(), ctx.variavel().tipo().getStart(), TipoEntrada.VARIAVEL, -1);
                                //System.out.println(nomeVariavel +"." + variavelRegistro.get(i)+ " TIPO: "+ variavelRegistro.get(i+1));
                            }
                        }
                    }
                } else { // Caso 1.2.2: verifica se é um tipo básico já declarado
                    //System.out.println("Entrou no caso 1.2.2!");
                    for (AlgumaGrammarParser.IdentificadorContext identc : ctx.variavel().identificador()) {
                        nomeVariavel = identc.IDENT(0).getText();
                        TipoEntrada tipo_ent = TipoEntrada.VARIAVEL;
                        int comprimentoArray = -1;
                        System.out.println("\t\tTentando mostrar " + nomeVariavel + ". Dimensao: " + identc.dimensao().getText());
                            
                        if (identc.dimensao() != null && !identc.dimensao().exp_aritmetica().isEmpty()) {
                            comprimentoArray = identc.dimensao().exp_aritmetica().size();
    
                            if (comprimentoArray > 0) {
                                // Assume the first expression defines the array size (you can extend this if needed)
                                comprimentoArray = Integer.parseInt(identc.dimensao().exp_aritmetica(0).getText());
                                tipo_ent = TipoEntrada.ARRAY;
                            }
                        }

                        // Verifica se pode ser uma função ou procedimento
                        if (TabelaDeSimbolos.tabelaFuncaoProcedimento.containsKey(nomeVariavel))
                            AlgumaSemanticoUtils.adicionarErroSemantico(identc.getStart(), 
                                "identificador " + nomeVariavel + " ja declarado anteriormente");
                        else{
                            System.out.println("\t\t\tAdicionando "+nomeVariavel+" de tipo "+tipoVariavel+". Com comprimento "+comprimentoArray);
                        
                            adicionaVariavelTabela(nomeVariavel, tipoVariavel, identc.getStart(), ctx.variavel().tipo().getStart(), tipo_ent, comprimentoArray); 
                            System.out.println("Buscando "+nomeVariavel+" na tabela. Tamanho: "+tabela.verificarTamanhoArray(nomeVariavel));
                        }
                    }
                }
            }
        // Caso 2: Declaração de tipo
        } else if (ctx.getText().contains("tipo")) { // Caso 3: verifica se é um novo tipo
            if (ctx.tipo().registro() != null) {
                ArrayList<String> variaveisRegistro = new ArrayList<String>();
                // Itera sobre os atributos do tipo
                for (AlgumaGrammarParser.VariavelContext vc : ctx.tipo().registro().variavel()) {
                    tipoVariavel = vc.tipo().getText();
                    
                    for (AlgumaGrammarParser.IdentificadorContext ic : vc.identificador()) {
                        //System.out.println("Ident variavel registro:"+ic.getText());
                        //System.out.println("Tipo variavel registro:"+tipoVariavel);
                        // Adiciona o IDENT e o tipo da variável
                        variaveisRegistro.add(ic.getText());
                        variaveisRegistro.add(tipoVariavel);
                    }
                }
                //System.out.println(variaveisRegistro);
                // Adiciona o tipo aos hashmap de registros
                tabelaRegistro.put(ctx.IDENT().getText(), variaveisRegistro);
            }
        // Caso 3: Constante    
        } else if (ctx.getText().contains("constante")){
            adicionaVariavelTabela(ctx.IDENT().getText(), ctx.tipo_basico().getText(), ctx.IDENT().getSymbol(), ctx.IDENT().getSymbol(), TipoEntrada.VARIAVEL, -1);
        // Caso 4: a declaração é uma variável   
        
        }
        return super.visitDeclaracao_local(ctx);
    }


   // Visita registro e cada uma das variáveis
    @Override 
    public Void visitRegistro(AlgumaGrammarParser.RegistroContext ctx) {
        
        return visitChildren(ctx); 
    
    }

    @Override
    public Void visitDeclaracao_global(AlgumaGrammarParser.Declaracao_globalContext ctx) {
        // Extração do nome da função
        tabelaEscopos = escoposAninhados.obterEscopoAtual();
        String funcName = ctx.IDENT().getText();
        TipoEntrada tipoEnt;

        System.out.println("Nome da funcao: "+funcName);
        // Extração do tipo de retorno
        TabelaDeSimbolos.AlgumaGrammar tiposRetorno = TabelaDeSimbolos.AlgumaGrammar.VOID;
        if (ctx.tipo_estendido() != null) {
            tiposRetorno = determinarTipoAlgumaGrammar(ctx.tipo_estendido().getText());
            System.out.println("O tipo de retorno da função é:"+tiposRetorno);
            tipoEnt = TipoEntrada.FUNCAO;
        }else{
            System.out.println("É procedimento!");
            tiposRetorno = AlgumaGrammar.VOID;
            tipoEnt = TipoEntrada.PROCEDIMENTO;
        }

        tabela.adicionar(funcName, tiposRetorno, tipoEnt, false, -1);
        
        // Extração dos tipos dos parâmetros
        List<TabelaDeSimbolos.AlgumaGrammar> tiposParametros = new ArrayList<>();
        if (ctx.parametros() != null) {
            for (var param : ctx.parametros().parametro()) {
                TabelaDeSimbolos.AlgumaGrammar paramType = determinarTipoAlgumaGrammar(param.tipo_estendido().getText());
                tiposParametros.add(paramType);
                
                if (paramType == AlgumaGrammar.INVALIDO) {
                    AlgumaSemanticoUtils.adicionarErroSemantico(param.getStart(),
                            "tipo " + param.tipo_estendido().getText() + " nao declarado");
                }
                tiposParametros.add(paramType);
                if (paramType == AlgumaGrammar.REGISTRO) {
                    String nomeRegistro = param.tipo_estendido().getText();
                    if (tabelaRegistro.containsKey(nomeRegistro)) {
                        ArrayList<String> variavelRegistro = tabelaRegistro.get(nomeRegistro);

                        for (var ident : param.identificador()) {
                            String nomeVariavel = ident.getText();
                            tabelaEscopos.adicionar(nomeVariavel, paramType, TipoEntrada.VARIAVEL, false, -1);
                            System.out.println("Adicionando variável: " + nomeVariavel + " com tipo " + TipoEntrada.VARIAVEL);
                            for (int i = 0; i < variavelRegistro.size(); i += 2) {
                                String nomeAtributo = nomeVariavel + "." + variavelRegistro.get(i);
                                AlgumaGrammar tipoAtributo = determinarTipoAlgumaGrammar(variavelRegistro.get(i + 1));
                                tabelaEscopos.adicionar(nomeAtributo, tipoAtributo, TipoEntrada.VARIAVEL, false, -1);
                                System.out.println("Adicionando atributo do registro: " + nomeAtributo + " com tipo " + tipoAtributo);
                            }
                        }
                    } else {
                        AlgumaSemanticoUtils.adicionarErroSemantico(param.getStart(),
                            "tipo de registro " + nomeRegistro + " nao declarado");
                    }
                } else {
                param.identificador().forEach(elemento -> {tabelaEscopos.adicionar(elemento.getText(), 
                    paramType, 
                    TipoEntrada.VARIAVEL, 
                    false, -1);});
                }
            }
        }

        // Criação da assinatura como uma arraylist do tipo de retorno e tipos de parâmetros
        ArrayList<TabelaDeSimbolos.AlgumaGrammar> assinatura = new ArrayList<>();
        assinatura.add(tiposRetorno);  // Adiciona o tipo do retorno
        assinatura.addAll(tiposParametros);  // Adiciona todos os tipos dos parâmetros

        // Armazena a função/procedimento
        tabelaFuncaoProcedimento.put(funcName, assinatura);
        return super.visitDeclaracao_global(ctx);

        }


    @Override
    public Void visitDecl_local_global(AlgumaGrammarParser.Decl_local_globalContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        // Identifica se é uma declaração local ou global
        if (ctx.declaracao_local() != null) {
            visitDeclaracao_local(ctx.declaracao_local());
        } else if (ctx.declaracao_global() != null)
            visitDeclaracao_global(ctx.declaracao_global());

        return super.visitDecl_local_global(ctx);
    }

    // Visita atribuições
    // @Override
    // public Void visitCmdAtribuicao(AlgumaGrammarParser.CmdAtribuicaoContext ctx) {
    //     tabela = escoposAninhados.obterEscopoAtual();
    //     String nomeVariavel = ctx.identificador().getText();
    //     boolean deferenciacao = ctx.start.getText().equals("^");
    //     boolean ref_memoria = ctx.expressao().getText().startsWith("&");
    //     AlgumaGrammar tipoExpressao = AlgumaSemanticoUtils.verificarTipo(tabela, ctx.expressao());

    //     //System.out.println("Visitando atribuição! Variável+" + ctx.identificador());
    //     //System.out.println("Inicio da expressao: "+ctx.start.getText()+".// ^?"+isDereferenced);
        
    //     if (tipoExpressao != AlgumaGrammar.INVALIDO) {
    //         // Caso a variável não tenha sido declarada, informa o erro
    //         if (!tabela.existe(nomeVariavel)) {
    //             AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
    //                 "identificador " + ctx.identificador().getText() + " nao declarado");
    //         } else {
    //             // Caso tenha sido declarada, verifica os demais casos
    //             AlgumaGrammar tipoVar = tabela.verificar(nomeVariavel);
    //             boolean isPointerVar = tabela.verificar_ponteiro(nomeVariavel);
    //             //System.out.println("Nome variavel: " + nomeVariavel + ". Tipo variavel:" + tipoVar + ". Pointer?" + isPointerVar);
    //             //System.out.println("Expressao: " + ctx.expressao() + " . Texto expressao: " + ctx.expressao().getText() + ". Tipo Expressao:" + tipoExpressao);

    //             // Se a variável é um ponteiro há 2 casos:
    //             if (isPointerVar){
    //                 /* Caso 1: Há deferenciação. Ex: distancia é ponteiro de int. ^distancia <- 400
    //                     * Testar se o tipo da variável é o mesmo da expressão.
    //                     * Testar se a variável é ponteiro.
    //                     * Erro? incompatível ^ */
    //                 if (deferenciacao && !ref_memoria) {
                        
    //                     if (tipoVar != tipoExpressao)
    //                         AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
    //                             "atribuicao nao compativel para " + ctx.start.getText() + nomeVariavel);
    //                 }    
    //                 /* Caso 2: Atribuir o endereço da expressão à variável.
    //                 * Verificar se há o símbolo &
    //                 * Verificar se a expressão possui o mesmo tipo da variável
    //                 * Verificaar se a variável é ponteiro */
    //                 else if (ref_memoria && !deferenciacao){
    //                     String identificadorExpressao = extractIndentificadorString(ctx.expressao().getText());
    //                     // Caso a variável seja um ponteiro e o identificador da expressão não seja null
    //                     if (identificadorExpressao != null) {
    //                         // Verifica se o identificador da expressão existe na tabela de símbolos e são
    //                         // de mesmo tipo
    //                         AlgumaGrammar tipoId = tabela.verificar(identificadorExpressao);
    //                         if (tipoId != tipoVar) {
    //                             AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
    //                                 "atribuicao nao compativel para " + nomeVariavel);
    //                         }
    //                     }
                    
    //                 // Exceções: Erro.
    //                 } else {
    //                     AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
    //                         "atribuicao nao compativel para " + nomeVariavel);
    //                 }
    //             }
    //             // Caso a variável seja um array
    //             else if (
    //                 (ctx.identificador().dimensao() != null) && (!ctx.identificador().dimensao().isEmpty())){
                    
    //                 // Verifica o tipo da expressão e do array a receber atribuição 
    //                 if (!tipoExpressao.equals(tipoVar)){
    //                     System.out.println("INCOMPATIVEL. Expressao "+ctx.expressao().getText()+". Tipo: "+tipoExpressao+". Variável: "+nomeVariavel+". Tipo array:"+tipoVar);
    //                     AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
    //                         "tipo de atribuicao incompativel para " + nomeVariavel);
    //                 }
    //             }
            
    //             // Caso a variável não seja ponteiro, verifica apenas o tipo
    //             // Atribuição entre expressão de tipo inteiro para variável real é compatível
    //             else if (!(tipoExpressao.name() == "INTEIRO" && tipoVar.name() == "REAL" ) && tipoExpressao != tipoVar){
                    
    //                 System.out.println("INCOMPATIVEL. Expressao de tipo: "+tipoExpressao+". Tipo var:"+tipoVar);
    //                 AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
    //                     "tipo de atribuicao incompativel para " + nomeVariavel);
    //             }
    //         }
    //     }
    //     return super.visitCmdAtribuicao(ctx);
    // }

    public Void visitCmdAtribuicao(AlgumaGrammarParser.CmdAtribuicaoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        //ctx.identificador().IDENT().forEach(element -> {System.out.println("Identificador:" +element);});
        
        String nomeVariavel = ctx.identificador().IDENT(0).getText();
        System.out.println("Avaliando a variavel "+nomeVariavel);
        
        boolean deferenciacao = ctx.start.getText().equals("^");
        boolean ref_memoria = ctx.expressao().getText().startsWith("&");
        
        AlgumaGrammar tipoExpressao = AlgumaSemanticoUtils.verificarTipo(tabela, ctx.expressao());
        AlgumaGrammar tipoVar = null;

        System.out.println("cmdAtribuicao: Variavel: "+nomeVariavel+". Tipo da expressao: "+tipoExpressao+". Nome COMPLETO:"+ctx.identificador().getText()+". Expressao: "+ctx.expressao().getText());
        
        // Caso a variável não tenha sido declarada, informa o erro
        if (!tabela.existe(nomeVariavel)) {
            System.out.println("\tIsh, não achou o "+nomeVariavel);
            AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
                "identificador " + ctx.identificador().getText() + " nao declarado");
        }
        // Satisfaz a condição se a variável for um registro e seu atributo não existir
        else if (!verificaAtributoRegistro(ctx.identificador())){
            nomeVariavel = nomeVariavel + "." + ctx.identificador().IDENT(1).getText();
            AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
            "identificador " + nomeVariavel + " nao declarado");
        }
        // Verificação de compatibilidade de tipos para expressões válidas
        else if (tipoExpressao != AlgumaGrammar.INVALIDO) {
            if (ctx.identificador().IDENT().size()>1)            
                nomeVariavel = nomeVariavel + "." + ctx.identificador().IDENT(1).getText();
            tipoVar = tabela.verificar(nomeVariavel); // Retorna o tipo da variável
            boolean isPointerVar = tabela.verificar_ponteiro(nomeVariavel);
            System.out.println("\tTipo da variável"+nomeVariavel+": "+tipoVar);


            if (isPointerVar) { // Verifica se a variável é um ponteiro
                if (deferenciacao && !ref_memoria) { // Trata caso de deferenciação
                    if (tipoVar != tipoExpressao)
                        AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
                            "atribuicao nao compativel para " + ctx.start.getText() + nomeVariavel);
                }
                else if (ref_memoria && !deferenciacao) { // Trata caso de atribuição à referência de memôria
                    String identificadorExpressao = extractIndentificadorString(ctx.expressao().getText());
                    
                    if (identificadorExpressao != null) { 
                        AlgumaGrammar tipoId = tabela.verificar(identificadorExpressao);
                        
                        if (tipoId != tipoVar) {
                            AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
                                "atribuicao nao compativel para " + nomeVariavel);
                        }
                    }
                } else {
                    AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
                        "atribuicao nao compativel para " + nomeVariavel);
                }
            }
            // Trata atribuição de arrays
            else if (ctx.identificador().dimensao() != null && !ctx.identificador().dimensao().exp_aritmetica().isEmpty()) {
                // Verifica se a expressão tem o mesmo tipo do array
                System.out.println("\tTipo da expressao: "+tipoExpressao+". Tipo da variavel: "+tipoVar);
                
                if (!tipoExpressao.equals(tipoVar)) {
                    AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
                        "atribuicao nao compativel para " + nomeVariavel + ctx.identificador().dimensao().getText());
                }
            }
            // Verifica compatibilidade de variáveis de não-ponteiros e não-arrays
            
            else if (!AlgumaSemanticoUtils.verificaCompatibilidade(tipoExpressao, tipoVar)) {
                AlgumaSemanticoUtils.adicionarErroSemantico(ctx.identificador().getStart(), 
                    "atribuicao nao compativel para " + nomeVariavel);
            }
        }
        return super.visitCmdAtribuicao(ctx);
    }
    

    private String extractIndentificadorString(String expression) {
        // Extrai o identificador após o símbolo '&''
        if (!expression.startsWith("&"))
            return null;

        return expression.substring(1).trim().split("\\s+")[0];
    }

    // Verifica se um atributo de um registro existe e adiciona um erro semâmntico   
    public boolean verificaAtributoRegistro(AlgumaGrammarParser.IdentificadorContext identificador){
        tabela = escoposAninhados.obterEscopoAtual();

        if (identificador.IDENT().size() > 1) {
            String nomevar = identificador.IDENT(0).getText() + "." + identificador.IDENT(1).getText();
            System.out.println("Identificador de registro com atributo: " + nomevar);
            
            if (!tabela.existe(nomevar)) 
                return false;
                
        }
        return true;
    }


    // Visita comandos de leitura
    @Override
    public Void visitCmdLeia(AlgumaGrammarParser.CmdLeiaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        String nomeVar = null;

        for (AlgumaGrammarParser.IdentificadorContext id : ctx.identificador()){
            nomeVar = id.IDENT(0).getText();
            boolean isRegistro = false;

            // Verifica se o identificador é um registro ou uma variável simples
            if (tabela.existe(nomeVar)) {
                AlgumaGrammar tipoVar = tabela.verificar(nomeVar);
                if (tipoVar == AlgumaGrammar.REGISTRO) {
                    isRegistro = true;
                }
            }
            if (!tabela.existe(nomeVar)) {
                AlgumaSemanticoUtils.adicionarErroSemantico(id.getStart(),
                    "identificador " + nomeVar + " nao declarado");
            } else if (isRegistro && id.IDENT().size() > 1) {
                // Se é um registro, verificar se o atributo é válido
                String nomeAtributo = nomeVar + "." + id.IDENT(1).getText();
                if (!tabela.existe(nomeAtributo)) {
                    AlgumaSemanticoUtils.adicionarErroSemantico(id.getStart(),
                        "identificador " + nomeAtributo + " nao declarado");
                }
            } else if (isRegistro && id.IDENT().size() == 1) {
                // Se é um registro, mas nenhum atributo foi especificado
                AlgumaSemanticoUtils.adicionarErroSemantico(id.getStart(),
                    "atributo nao especificado para o registro " + nomeVar);
            }
            
        }
        return super.visitCmdLeia(ctx);
    }

    // Visita parcelas
    @Override
    public Void visitParcela(ParcelaContext ctx) {
       return super.visitParcela(ctx);
    }


    // Visita parcelas não unárias
    @Override
    public Void visitParcela_nao_unario(Parcela_nao_unarioContext ctx) {
        if (ctx.identificador() != null) {
            IdentificadorContext idc = ctx.identificador();
            
            for (var id : idc.IDENT()) {
                String nomeVar = id.getText();
                //System.out.println("Parcela Unaria. Nome var: " + nomeVar);
                boolean declarado = false;
                
                for (var tabela : escoposAninhados.percorrerEscoposAninhados()) {
                    if (tabela.existe(nomeVar)) {
                        declarado = true;
                    }
                }
                if (!declarado) {
                    AlgumaSemanticoUtils.adicionarErroSemantico(id.getSymbol(), 
                        "identificador " + nomeVar + " nao declarado");
                }
            }
        }
        // Caso o if acima não seja satisfeito, a parcela não unária é uma cadeira
        return super.visitParcela_nao_unario(ctx);
    }
    
    @Override
    public Void visitParcela_unario(AlgumaGrammarParser.Parcela_unarioContext ctx) {
        if (ctx.identificador() != null) {
            IdentificadorContext idc = ctx.identificador();
            String nomeCompleto = idc.IDENT(0).getText();  

            // Construir o nome completo se houver mais de um IDENT (ex: "vinho.nome")
            if (idc.IDENT().size() > 1) {
                for (int i = 1; i < idc.IDENT().size(); i++) {
                    nomeCompleto += "." + idc.IDENT(i).getText();
                }
            }
            
            System.out.println("Visitor Unario vendo " + nomeCompleto);

            
            // Havendo dimensão, trata como Array
            if (idc.dimensao() != null && !idc.dimensao().exp_aritmetica().isEmpty()) {
                
                if (!tabela.existe(nomeCompleto)) {
                    System.out.println("Encontrou "+nomeCompleto);
                    AlgumaSemanticoUtils.adicionarErroSemantico(idc.IDENT(0).getSymbol(), 
                        "identificador " + nomeCompleto + " nao declarado");

                } else {
                    TabelaDeSimbolos.AlgumaGrammar tipoArray = tabela.verificar(nomeCompleto);
                    
                    if (tipoArray != AlgumaGrammar.INTEIRO && tipoArray != AlgumaGrammar.REAL) {
                        AlgumaSemanticoUtils.adicionarErroSemantico(idc.IDENT(0).getSymbol(), 
                            "tipo de array " + nomeCompleto + " nao compativel");
                    }
                }
            } else {
                boolean declarado = false;

                // Procura pelo identificador em todos os escopos aninhados
                for (var tabela : escoposAninhados.percorrerEscoposAninhados()) {
                    if (tabela.existe(nomeCompleto)) {
                        declarado = true;
                        break;
                    }
                }
                if (!declarado) { // Gera um erro semântico se não foi encontrado
                    AlgumaSemanticoUtils.adicionarErroSemantico(idc.IDENT(0).getSymbol(), 
                        "identificador " + nomeCompleto + " nao declarado");
                } 
            }
        } 
        // Trata chamada de funções e procedimento
        else if (ctx.IDENT() != null && ctx.expressao() != null) {
            String nomeFuncProc = ctx.IDENT().getText();
            ArrayList<TabelaDeSimbolos.AlgumaGrammar> assinatura = tabelaFuncaoProcedimento.get(nomeFuncProc);
            
            if (assinatura == null) {
                AlgumaSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), 
                    "funcao ou procedimento " + nomeFuncProc + " nao declarado");
                return super.visitParcela_unario(ctx);
            }

            if (ctx.expressao().size() != assinatura.size() - 1) { 
                AlgumaSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), 
                    "incompatibilidade de parametros na chamada de " + nomeFuncProc);
                return super.visitParcela_unario(ctx);
            }

            for (int i = 0; i < ctx.expressao().size(); i++) {
                TabelaDeSimbolos.AlgumaGrammar actualType = AlgumaSemanticoUtils.verificarTipo(tabela, ctx.expressao(i));
                TabelaDeSimbolos.AlgumaGrammar expectedType = assinatura.get(i + 1); 
                if (!actualType.equals(expectedType)) {
                    AlgumaSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), 
                        "incompatibilidade de parametros na chamada de " + nomeFuncProc);
                    break;
                }
            }
        }
        return super.visitParcela_unario(ctx);
    }
    
    // Visita parcelas unárias
    //@Override
    // public Void visitParcela_unario(Parcela_unarioContext ctx) {
    //     //System.out.println("\tVisitando parcela unaria: " + ctx.getText());
    
    //     if (ctx.identificador() != null) {
    //         IdentificadorContext idc = ctx.identificador();
    //         // Salva o nome completo da variável (variavel.atributo)
    //         String nomeCompleto = idc.getText();  
    //         //System.out.println("Procurando pelo identificador completo: " + nomeCompleto);
    //         boolean declarado = false;
            
    //         // Procura pelo identificador em todos os escopos aninhados
    //         for (var tabela : escoposAninhados.percorrerEscoposAninhados()) {
    //             if (tabela.existe(nomeCompleto)) {
    //                 declarado = true;
    //                 //System.out.println("Olha, ele achou!"+nomeCompleto);
    //                 break; // Finaliza a procura quando o identificador é encontrado
                    
    //             }
    //         }

    //         // Gera um erro semântico se não foi encontrado
    //         if (!declarado) {
    //             AlgumaSemanticoUtils.adicionarErroSemantico(idc.IDENT(0).getSymbol(), 
    //                 "identificador " + nomeCompleto + " nao declarado");
    //         }
    //         // Checa se a dimensão do array está sendo acessada corretamente
    //         else if ((idc.dimensao()!= null) && (!idc.dimensao().isEmpty())){
    //             System.out.println("\tDimensão do array "+nomeCompleto+": "+idc.dimensao().getText());
    //             AlgumaGrammar tipoArray = tabela.verificar(nomeCompleto);
    //             if (tipoArray == AlgumaGrammar.INVALIDO){
    //                 System.out.println("ERRO! tipo do array:"+tipoArray);
    //                 AlgumaSemanticoUtils.adicionarErroSemantico(idc.IDENT(0).getSymbol(), 
    //                     "acesso a dimensao de array nao declarada ou tipo invalido " + nomeCompleto);
    //             }

    //         }
    //     } // Caso seja uma função ou procedimento 
    //     else if (ctx.IDENT() != null && ctx.expressao() != null) {
    //         // Extrai o nome da função/procedimento
    //         String nomeFuncProc = ctx.IDENT().getText();
    
    //         System.out.println("Entrando na funcao "+nomeFuncProc);
    //         // Recupera a assinatura da função pelo hashmap
    //         ArrayList<TabelaDeSimbolos.AlgumaGrammar> assinatura = tabelaFuncaoProcedimento.get(nomeFuncProc);
    //         if (assinatura == null) {
    //             AlgumaSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), 
    //                 "funcao ou procedimento " + nomeFuncProc + " nao declarado");
    //             return super.visitParcela_unario(ctx);
    //         }
    
    //         // Checa a quantidade de parâmetros
    //         if (ctx.expressao().size() != assinatura.size() - 1) { // Exclui o tipo de retorno da quantidade
    //             AlgumaSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), 
    //                 "incompatibilidade de parametros na chamada de " + nomeFuncProc);
    //             return super.visitParcela_unario(ctx);
    //         }
    
    //         // Checagem de tipos de parâmetros
    //         for (int i = 0; i < ctx.expressao().size(); i++) {
    //             TabelaDeSimbolos.AlgumaGrammar actualType = AlgumaSemanticoUtils.verificarTipo(tabela, ctx.expressao(i));
    //             System.out.println("\tExpressao: "+ctx.expressao(i).getText()+". Tipo:"+actualType); 
                
    //             TabelaDeSimbolos.AlgumaGrammar expectedType = assinatura.get(i + 1); // Pula o 1º elemento (tipo de retorno)
    //             //if ((!actualType.equals(expectedType))||(actualType == AlgumaGrammar.INTEIRO && expectedType == AlgumaGrammar.REAL)) {
    //             if (!actualType.equals(expectedType)) {
    //                 System.out.println("\t\tERRO NA FUNCAO/PROC "+nomeFuncProc+". Tipo atual: "+actualType+". Tipo esperado: "+expectedType);
    //                 AlgumaSemanticoUtils.adicionarErroSemantico(ctx.IDENT().getSymbol(), 
    //                     "incompatibilidade de parametros na chamada de " + nomeFuncProc);
    //                 break;
    //             }
    //         }
    //     }

    //     return super.visitParcela_unario(ctx);
    // }


    @Override
    public Void visitFator_logico(AlgumaGrammarParser.Fator_logicoContext ctx) {
        return super.visitFator_logico(ctx);
    }

    @Override
    public Void visitTermo_logico(AlgumaGrammarParser.Termo_logicoContext ctx) {
        return super.visitTermo_logico(ctx);
    }

    @Override
    public Void visitTermo(AlgumaGrammarParser.TermoContext ctx) {
        return super.visitTermo(ctx);
    }

    @Override
    public Void visitFator(AlgumaGrammarParser.FatorContext ctx) {
        return super.visitFator(ctx);
    }
        

    @Override
    public Void visitCmdRetorne(AlgumaGrammarParser.CmdRetorneContext ctx) {
        // Obter o escopo atual
        tabela = escoposAninhados.obterEscopoAtual();

        // Verificar se o escopo atual é de uma função
        boolean dentroDeFuncao = false;
        for (var escopo : escoposAninhados.percorrerEscoposAninhados()) {

        // TO-DO: IMPLEMENTAR VERIFICAÇÃO SE O ESCOPO ATUAL É UMA FUNÇÃO
        
        }

        // Se não estiver dentro de uma função, adicionar erro semântico
        if (!dentroDeFuncao) {
            AlgumaSemanticoUtils.adicionarErroSemantico(ctx.getStart(),
                "comando retorne nao permitido nesse escopo");
        }

        return super.visitCmdRetorne(ctx);
    }

    @Override
    public Void visitCmdPara(AlgumaGrammarParser.CmdParaContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();
        String nomeVariavel = ctx.IDENT().getText();

        System.out.println("Nome da variavel do PARA: "+nomeVariavel);
        if (!tabela.existe(nomeVariavel)) {
            tabela.adicionar(nomeVariavel, TabelaDeSimbolos.AlgumaGrammar.INTEIRO, TipoEntrada.VARIAVEL, false, -1);
    
        } 

        // Avaliar o valor inicial e o valor final como expressões aritméticas
        //AlgumaGrammar tipoInicial = AlgumaSemanticoUtils.verificarTipo(tabela, ctx.exp_aritmetica(0));
        //AlgumaGrammar tipoFinal = AlgumaSemanticoUtils.verificarTipo(tabela, ctx.exp_aritmetica(1));

    // Executar os filhos do nó atual
    visitChildren(ctx);


        return super.visitCmdPara(ctx);
    }

    @Override
    public Void visitCmdSe(AlgumaGrammarParser.CmdSeContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        var conditions = ctx.expressao().termo_logico().get(0);


        // Leitura dos termos

        // Caso não haja o operador "ou" (op_logico_1) 
        // => há apenas termos lógicos (fatores lógicos e op_logico_2 )
        if (ctx.expressao().op_logico_1().isEmpty()){
            System.out.println(conditions.fator_logico().size());
            
            // Lê cada condição do comando 'Se'
            for (var fator_logico: conditions.fator_logico()){
                //System.out.println("\t\tImprimindo 'fator'"+fator_logico.getText());
                
                // Varre cada uma das expressões
                for (var exps: fator_logico.parcela_logica().exp_relacional().exp_aritmetica()){
                    for (var termo: exps.termo()){
                        //System.out.println("\t\t\t\tImprimindo 'termo'"+termo.getText());


                    }

                }
            }

        }
        // Há operador 'ou':
        else{
            //System.out.println("To-do");
        }

        AlgumaSemanticoUtils.verificarTipo(tabela, ctx.expressao());

        return super.visitCmdSe(ctx);
    }

    @Override
    public Void visitCmdEnquanto(AlgumaGrammarParser.CmdEnquantoContext ctx) {
        tabela = escoposAninhados.obterEscopoAtual();

        AlgumaSemanticoUtils.verificarTipo(tabela, ctx.expressao());

        return super.visitCmdEnquanto(ctx);
    }
}