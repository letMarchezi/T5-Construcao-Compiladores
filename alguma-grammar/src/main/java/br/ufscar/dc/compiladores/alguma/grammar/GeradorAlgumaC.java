package br.ufscar.dc.compiladores.alguma.grammar;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.AlgumaGrammar;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.TipoEntrada;

import br.ufscar.dc.compiladores.alguma.grammar.AlgumaSemantico;

public class GeradorAlgumaC extends AlgumaGrammarBaseVisitor<Void> {
    private StringBuilder codigoC = new StringBuilder();
    TabelaDeSimbolos tabela;

    // TabelaDeSimbolos tabelaEscopos;
    // static Escopos escoposAninhados = new Escopos();

    public String getHtml() {
        return codigoC.toString();
    }
    
    
    public String converterTipo(String tipo_grammar){
        String tipo_c = null;
        switch (tipo_grammar){
            case "inteiro":
                tipo_c = "int";
        }

        return tipo_c;
    }
    // Inicia o programa
    @Override
    public Void visitPrograma(AlgumaGrammarParser.ProgramaContext ctx) {
        //StringBuilder inicioC = new StringBuilder();
        codigoC.append("#include <stdio.h>\n" + "#include <stdlib.h>\n" + "int main(){\n");
        // Adiciona estilização do HTML
    
        Void result = super.visitPrograma(ctx);
        
        codigoC.append("}");

        return result;
        // // Após visitar todos os bloco, chama o agendador
        // planner.planejarEstudos(false);

        // // Concatena o html da tabela do cronograma de estudos com as informações gerais
        // inicio_html.append(planner.mostrarAgenda());
        // html = inicio_html.append(html);
        // html = html.append("\n\t</body>\n</html>");
    }


    // Registra os dias da semana e o período de tempo disponível para estudos
    // Insere as atividades como tempo ocupado 
    
    @Override
    public Void visitDeclaracao_local(AlgumaGrammarParser.Declaracao_localContext ctx) {
        
        if (ctx.variavel() != null){
            String tipo = ctx.variavel().tipo().getText();
            String tipo_c = converterTipo(tipo);
            codigoC.append(tipo_c+" ");

            List<String> lista_ident = new ArrayList<>();
            codigoC.append("\t");
            for (var ident: ctx.variavel().identificador()){
                codigoC.append(ident.getText()+";");
                tabela.adicionar(ident.getText(), AlgumaSemantico.determinarTipoAlgumaGrammar(tipo), TipoEntrada.VARIAVEL, false, -1);
            } 

        }

        return super.visitDeclaracao_local(ctx);
    }

    @Override
    public Void visitCmdLeia(AlgumaGrammarParser.CmdLeiaContext ctx) {
        
        for (var ident: ctx.identificador()){
            String tipo_escrita = null;
            AlgumaGrammar tipo = tabela.verificar(ident.getText());
            if(tipo==AlgumaGrammar.INTEIRO){
                tipo_escrita = "%d";
            }
               
            codigoC.append("scanf('"+tipo_escrita+"', &"+ident+");\n");
        }

        return super.visitCmdLeia(ctx);
    }
    @Override
    public Void visitCmdEscreva(AlgumaGrammarParser.CmdEscrevaContext ctx) {
        
        for (int i=0; i < ctx.expressao().size(); i++){
            var exp = ctx.expressao(i);

            String tipo = determinarTipoExpressao(exp);
            
            codigoC.append("printf(%d,)");
        }

        return super.visitCmdEscreva(ctx);
    }

    @Override
    public Void visitDecl_local_global(AlgumaGrammarParser.Decl_local_globalContext ctx) {
        

        return super.visitDecl_local_global(ctx);
    }

    
    
}
